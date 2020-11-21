import os
import cv2 as cv
import numpy as np
import matplotlib.pyplot as plt
import pdb

from add_pieces_mosaic import *
from parameters import *


def load_pieces(params: Parameters):
    # citeste toate cele N piese folosite la mozaic din directorul corespunzator
    # toate cele N imagini au aceeasi dimensiune H x W x C, unde:
    # H = inaltime, W = latime, C = nr canale (C=1  gri, C=3 color)
    # functia intoarce pieseMozaic = matrice N x H x W x C in params
    # pieseMoziac[i, :, :, :] reprezinta piesa numarul i
    
    dir_path = params.small_images_dir
    filenames = os.listdir(dir_path)
    images = []
    for image_name in filenames:
        if params.is_grayscale == False:
            # cititm pozele ca rgb
            img_current = cv.imread(dir_path + image_name)
        else:
            # citim pozele ca grayscale
            img_current = cv.imread(dir_path + image_name, cv.IMREAD_GRAYSCALE)

        images.append(img_current)

    images = np.array(images, np.float32)

    # citeste imaginile din director

    if params.show_small_images:
        for i in range(10):
            for j in range(10):
                plt.subplot(10, 10, i * 10 + j + 1)
                # OpenCV reads images in BGR format, matplotlib reads images in RBG format
                im = images[i * 10 + j].copy()
                # BGR to RGB, swap the channels
                im = im[:, :, [2, 1, 0]]
                plt.imshow(im)
        plt.show()

    params.small_images = images


def compute_dimensions(params: Parameters):
    # calculeaza dimensiunile mozaicului
    # obtine si imaginea de referinta redimensionata avand aceleasi dimensiuni
    # ca mozaicul

    # completati codul
    # calculeaza automat numarul de piese pe verticala
    # pentru a pastra acelasi ration ca imaginea initiala (H - W) 
    
    if params.is_grayscale == False:
        H, W, C  = params.image.shape
        h, w, c = params.small_images[0].shape
    
    else:
        H, W  = params.image.shape
        h, w = params.small_images[0].shape


    # vreau sa pastrez ratio H/W din imaginea initiala deci voi inmulti H/W cu noua latime 
    # (arams.num_pieces_vertical * h) si impart la h ca sa aflu nr de piese care incap pe verticala
    params.num_pieces_vertical = H * params.num_pieces_horizontal * w // (h * W)

    # redimensioneaza imaginea
    new_h = params.num_pieces_vertical * h
    new_w = params.num_pieces_horizontal * w
    params.image_resized = cv.resize(params.image, (new_w, new_h))


def build_mosaic(params: Parameters):
    # incarcam imaginile din care vom forma mozaicul
    load_pieces(params)
    # calculeaza dimensiunea mozaicului
    compute_dimensions(params)

    img_mosaic = None
    if params.layout == 'caroiaj':
        if params.hexagon is True:
            img_mosaic = add_pieces_hexagon(params)
        else:
            img_mosaic = add_pieces_grid(params)
    elif params.layout == 'aleator':
        img_mosaic = add_pieces_random(params)
    else:
        print('Wrong option!')
        exit(-1)

    return img_mosaic
