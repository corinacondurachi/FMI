import sys
import cv2 as cv
import numpy as np
import copy

from parameters import *
from select_path import *

import pdb

def compute_energy(img):
    """
    calculeaza energia la fiecare pixel pe baza gradientului
    :param img: imaginea initiala
    :return:E - energia
    """
    # urmati urmatorii pasi:
    # 1. transformati imagine in grayscale
    # 2. folositi filtru sobel pentru a calcula gradientul in directia X si Y
    # 3. calculati magnitudinea pentru fiecare pixel al imaginii
    E = np.zeros((img.shape[0],img.shape[1]))
    
    img_gray = cv.cvtColor(img, cv.COLOR_BGR2GRAY)

    sobelx = cv.Sobel(img_gray, cv.CV_64F,1,0)
    sobely = cv.Sobel(img_gray, cv.CV_64F,0,1)

    E = np.abs(sobelx) + np.abs(sobely)

    return E

def show_path(img, path, color):
    new_image = img.copy()
    for row, col in path:
        new_image[row, col] = color

    E = compute_energy(img)
    new_image_E = img.copy()
    new_image_E[:,:,0] = E.copy()
    new_image_E[:,:,1] = E.copy()
    new_image_E[:,:,2] = E.copy()

    for row, col in path:
        new_image_E[row, col] = color
    cv.imshow('path img', np.uint8(new_image))
    cv.imshow('path E', np.uint8(new_image_E))
    cv.waitKey(1000)


def delete_path(img, path):
    """
    elimina drumul vertical din imagine
    :param img: imaginea initiala
    :path - drumul vertical
    return: updated_img - imaginea initiala din care s-a eliminat drumul vertical
    """
    updated_img = np.zeros((img.shape[0], img.shape[1] - 1, img.shape[2]), np.uint8)
    for i in range(img.shape[0]):
        col = path[i][1]
        # copiem partea din stanga
        updated_img[i, :col] = img[i, :col].copy()
        # copiem partea din dreapta
        updated_img[i, col:] = img[i, col + 1:].copy()
        
    return updated_img

def decrease_width(params: Parameters, num_pixels, img):

    for i in range(num_pixels):
        print('Eliminam drumul vertical numarul %i dintr-un total de %d.' % (i+1, num_pixels))
        # calculeaza energia dupa ecuatia (1) din articol                
        E = compute_energy(img)
        path = select_path(E, params.method_select_path)
        if params.show_path:
            show_path(img, path, params.color_path)
        img = delete_path(img, path)

    cv.destroyAllWindows()
    return img

def decrease_height(params: Parameters, num_pixels, img):

    img = np.rot90(img)
    for i in range(num_pixels):
        print('Eliminam drumul vertical numarul %i dintr-un total de %d.' % (i+1, num_pixels))

        # calculeaza energia dupa ecuatia (1) din articol                
        E = compute_energy(img)
        path = select_path(E, params.method_select_path)
        if params.show_path:
            show_path(img, path, params.color_path)
        img = delete_path(img, path)

    cv.destroyAllWindows()
    img  = np.rot90(img,3)
    return img


def amplify_content(params: Parameters, img):

    new_width = int(img.shape[1] * params.factor_amplification)
    new_height = int(img.shape[0] * params.factor_amplification)
    dim = (new_width, new_height)

    # resized image
    enlarged_img = cv.resize(img, dim)

    num_pixels_height = new_height - img.shape[0]
    num_pixels_width = new_width - img.shape[1]

    image_decreased_width = decrease_width(params, num_pixels_width, enlarged_img)
    image_decreased = decrease_height(params, num_pixels_height, image_decreased_width)

    return image_decreased


def delete_object(params: Parameters, img, x0, y0, w, h):

    # in acest caz voi elima pe inaltime 'w' pixeli 
    if w <= h:
        # retin cati pixeli am eliminat (w pixeli in total)
        for i in range(w):
            print('Eliminam drumul vertical numarul %i dintr-un total de %d.' % (i+1, w))
            # calculeaza energia dupa ecuatia (1) din articol                
            E = compute_energy(img)
            path = select_dynamic_programming_path(E, y0, x0,h)
            if params.show_path: 
                show_path(img, path, params.color_path)
            img = delete_path(img, path)

    # in acest caz voi elima pe latime 'h' pixeli 
    else:
        img = np.rot90(img)
        for i in range(h):
            print('Eliminam drumul vertical numarul %i dintr-un total de %d.' % (i+1, h))
            # calculeaza energia dupa ecuatia (1) din articol                
            E = compute_energy(img)
            # calculez coordonata punctului din stanga sus (latimea_imaginii - x0 - w; y0)
            path = select_dynamic_programming_path(E, E.shape[0] - x0 - w, y0 ,w)
            if params.show_path: 
                show_path(img, path, params.color_path)
            img = delete_path(img, path)
        img  = np.rot90(img,3)

    cv.destroyAllWindows()
    return img



def resize_image(params: Parameters):

    img = params.image.copy() # copiaza imaginea originala

    if params.resize_option == 'micsoreazaLatime':
        # redimensioneaza imaginea pe latime
        resized_image = decrease_width(params, params.num_pixels_width,img)
        return resized_image

    elif params.resize_option == 'micsoreazaInaltime':
        #TODO: scrieti codul 
        resized_image = decrease_height(params, params.num_pixel_height,img)
        return resized_image
    
    elif params.resize_option == 'amplificaContinut':

        amplified_image = amplify_content(params, img)
        return amplified_image

    elif params.resize_option == 'eliminaObiect':

        r = cv.selectROI(np.uint8(img))
        image_without_object = delete_object(params,img,r[0],r[1],r[2],r[3])
        print(r)
        return image_without_object

    else:
        print('The option is not valid!')
        sys.exit(-1)