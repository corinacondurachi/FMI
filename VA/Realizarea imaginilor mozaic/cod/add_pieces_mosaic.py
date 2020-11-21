from parameters import *
import numpy as np
import pdb
import timeit
import os

# pentru a nu le calcula de fiecare data
def get_mean_color_small_images(params: Parameters, c):

    N = params.small_images.shape[0]
    mean_color_pieces = np.zeros((N,c))
    for i in range(N):
        current_image = params.small_images[i].copy()
        # calculez pe fiecare canal (3 pt RGB)
        if params.is_grayscale == False:
            for k in range(c):
                mean_color_pieces[i,k] = np.float32(current_image[:,:,k].mean()) 
        # un singur canal pt grayscale
        else:
            for k in range(c):
                mean_color_pieces[i,k] = np.float32(current_image[:,:].mean()) 
    
    return mean_color_pieces

# sortez indicii pentru partea cu vecini adiacenti diferiti
def get_sorted_indexes(mean_color_crop, mean_color_pieces):
    # nu mai are rost sa aplic sqrt deoarece nu schimba cu nimic ordinea
    distances = np.sum((mean_color_crop - mean_color_pieces)**2, axis = 1)
    return np.argsort(distances)

#construiesc o masca pentru hexagoane
def get_mask_hexagon(params: Parameters,H,W):
    j_min = int(np.floor(W/3))
    j_max = int(np.ceil(W * 2/3))
    mijloc = H // 2
    
    if params.is_grayscale == False:
        mask = np.zeros((H,W,1))
    else:
        mask = np.zeros((H,W))

    for i in range(mijloc):
        for j in range(max(0,j_min),min(j_max,W)):
            mask[i,j] = 1
            mask[H-i-1,j] = 1
        j_min -= 1
        j_max += 1
    return mask

# verific daca avem vecini la fel pentru hexagoane
def check_neighbors(matrice, i, j, w, h, W, H, index):
    # sus
    if i - 2 >= 0 and matrice[i - 2,j] == index:
        return False
    # stanga sus
    if i - 1 >= 0 and j - 1 >= 0 and matrice[i - 1, j - 1] == index:
        return False
    # stanga jos
    if i + 1 < h+H and j - 1 >= 0 and matrice[i + 1, j - 1] == index:
        return False
     # dreapta sus
    if i - 1 >= 0 and j + 1 < W + w and matrice[i - 1, j + 1] == index:
        return False
     # dreapta jos
    if i + 1 < h + H and j + 1 < W + w and matrice[i + 1,j + 1] == index:
        return False

    return True

         
def add_pieces_grid(params: Parameters):
    start_time = timeit.default_timer()
    img_mosaic = np.zeros(params.image_resized.shape, np.uint8)
    if params.is_grayscale == False:
        N, H, W, C = params.small_images.shape
        h, w, c = params.image_resized.shape
    else:
        N, H, W  = params.small_images.shape
        h, w  = params.image_resized.shape
        c = 1

    num_pieces = params.num_pieces_vertical * params.num_pieces_horizontal
    # retin indicele imaginii pusa in pozitia aceea ca sa nu am piese adiacente identice
    matrice = np.empty((params.num_pieces_vertical, params.num_pieces_horizontal)) 

    if params.criterion == 'aleator':
        for i in range(params.num_pieces_vertical):
            for j in range(params.num_pieces_horizontal):
                index = np.random.randint(low=0, high=N, size=1)
                img_mosaic[i * H: (i + 1) * H, j * W: (j + 1) * W, :] = params.small_images[index]
                print('Building mosaic %.2f%%' % (100 * (i * params.num_pieces_horizontal + j + 1) / num_pieces))

    elif params.criterion == 'distantaCuloareMedie':

        mean_color_pieces = get_mean_color_small_images(params, c)
        for i in range(params.num_pieces_vertical):
            for j in range(params.num_pieces_horizontal):
                if params.is_grayscale == False:
                    # subportiunea din imaginea mare
                    img_crop = params.image_resized [i * H: (i + 1) * H, j * W: (j + 1) * W, :]
                else:
                    img_crop = params.image_resized [i * H: (i + 1) * H, j * W: (j + 1) * W]

                # calculez media culorilor subimaginii
                mean_color_crop = np.mean(img_crop, axis = (0,1))
                # distantele euclidiene
                sorted_indexes = get_sorted_indexes(mean_color_crop, mean_color_pieces)
                # iau cel mai potrivita portiune
                index = sorted_indexes[0]
            
                # varific daca vecinii trebuie sa fie diferiti
                if params.different_neighbors == True:

                    # prima linie o verific doar cu cea din dreapta. Daca sunt egale, il iau pe urmatorul
                    if i == 0 and j > 0:
                        if matrice[i][j-1] == index:
                            index = sorted_indexes[1]

                    # prima coloana o verific doar cu cea de deasupra. Daca sunt egale, il iau pe urmatorul
                    if j == 0 and i > 0:
                        if matrice[i-1][j] == index:
                            index = sorted_indexes[1]    

                    # ma aflu in mijlocul pozei        
                    if i > 0 and j > 0:
                        k = 0
                        while sorted_indexes[k] == matrice[i-1][j] or sorted_indexes[k] == matrice[i][j-1]:
                            k += 1
                        index = sorted_indexes[k]
                
                # adaug indicele in matrice       
                matrice[i,j] = index
                if params.is_grayscale == False:
                    # adaug imaginea in mozaic
                    img_mosaic[i * H: (i + 1) * H, j * W: (j + 1) * W, :] = params.small_images[index]
                else:
                    img_mosaic[i * H: (i + 1) * H, j * W: (j + 1) * W] = params.small_images[index]

                print('Building mosaic %.2f%%' % (100 * (i * params.num_pieces_horizontal + j + 1) / num_pieces))    
        
    else:
        # print('Error! unknown option %s' % params.criterion)
        exit(-1)

    end_time = timeit.default_timer()
    print('Running time: %f s.' % (end_time - start_time))

    return img_mosaic


def add_pieces_random(params: Parameters):
    start_time = timeit.default_timer()

    if params.is_grayscale == False:
        N, H, W, C = params.small_images.shape
        h, w, c = params.image_resized.shape
    else:
        N, H, W = params.small_images.shape
        h, w = params.image_resized.shape
        c = 1

    if params.is_grayscale == False:
        img_mosaic = np.zeros((h + H, w + W, c), np.uint8)
    else:
        img_mosaic = np.zeros((h + H, w + W), np.uint8)
    mean_color_pieces = get_mean_color_small_images(params, c)
    # creez o imagine noua adaugand un padding atat jos cat si in dreapta
    if params.is_grayscale == False:
        image_padding = np.zeros((H + h, W + w, c), np.uint8)
    else:
        image_padding = np.zeros((H + h, W + w), np.uint8)
 
    if params.is_grayscale == False:
        # copiez in ea imaginea initiala
        image_padding[:-H, :-W, :] = params.image_resized.copy()
    else:
        image_padding[:-H, :-W] = params.image_resized.copy()

    #creez o matrice in care retin pozitiile nealese
    free_pixels = np.ones((img_mosaic.shape[0], img_mosaic.shape[1]), dtype = np.int)
    for i in range(free_pixels.shape[0]):
        for j in range (free_pixels.shape[1]):
            free_pixels[i,j] = i * free_pixels.shape[1] + j

    # marchez cu -1 paddingul pentru a nu alege sa pun imagini acolo 
    free_pixels[h:,:] = -1
    free_pixels[:,w:] = -1

    # aleg puncte random si pun cea mai apropaiata imagine in ele
    while True:
        free = free_pixels[free_pixels > -1]
        if len(free) == 0:
            break

        index  = np.random.randint(low = 0, high = len(free), size = 1)
        row = int(free[index] / free_pixels.shape[1])
        col = int(free[index] % free_pixels.shape[1])

        if params.is_grayscale == False:
            image_crop = image_padding[row:row + H, col: col + W, :]
        else:
            image_crop = image_padding[row:row + H, col: col + W]
        mean_color_crop = np.mean(image_crop, axis = (0,1))
        sorted_indexes = get_sorted_indexes(mean_color_crop, mean_color_pieces)

        # iau cel mai potrivita portiune
        index = sorted_indexes[0]
        if params.is_grayscale == False:
            # adaug piesa in mosaic
            img_mosaic[row:row + H, col: col + W, :] = params.small_images[index]
        else:
            img_mosaic[row:row + H, col: col + W] = params.small_images[index]
        # marchez portiunea ca fiind ocupata astfel incat sa nu mai fie selectata
        free_pixels[row:row + H, col: col + W] = -1


    end_time = timeit.default_timer()
    print('Running time: %f s.' % (end_time - start_time))

    if params.is_grayscale == False:
        # elimin padding-ul
        img_mosaic = img_mosaic[:h, :w, :]
    else:
        img_mosaic = img_mosaic[:h, :w]
    return img_mosaic


def add_pieces_hexagon(params: Parameters):
    start_time = timeit.default_timer()

    if params.is_grayscale == False:
        N, H, W, C = params.small_images.shape
        h, w, c = params.image_resized.shape
    else:
        N, H, W = params.small_images.shape
        h, w = params.image_resized.shape
        c = 1

    if params.is_grayscale == False:
        img_mosaic = np.zeros((h + 2 * H, w + 2 * W, c), np.uint8)
    else:
        img_mosaic = np.zeros((h + 2 * H, w + 2 * W), np.uint8)

    mean_color_pieces = get_mean_color_small_images(params, c)
    # creez o imagine noua adaugand un padding pe toata laturile
    if params.is_grayscale == False:
        image_padding = np.zeros((h + 2 * H, w + 2 * W, c), np.uint8)
    else:
        image_padding = np.zeros((h + 2 * H, w + 2 * W), np.uint8)

    if params.is_grayscale == False:
        # copiez in ea imaginea initiala
        image_padding[H:-H, W:-W, :] = params.image_resized.copy()
    else:
        image_padding[H:-H, W:-W] = params.image_resized.copy()

    # construiesc masca
    mask = get_mask_hexagon(params,H,W)
    # retin indicele imaginii pusa in pozitia aceea ca sa nu am piese adiacente identice
    matrice = np.empty((h + 2 * H, w + 2 * W)) 

    first_row_start = H//2
    row_index = 1

    # completez mai intai coloanele pare (0,2,4..)
    for i in range(first_row_start, image_padding.shape[0] - H, H):
        col_index = 0
        for j in range(0, image_padding.shape[1] - W, int(W + 1/3 * W)):
            patch = image_padding[i:i+H, j:j+W]
            mean_patch = np.mean(patch, axis = (0,1))
            # calculez toti indicii
            indexes = get_sorted_indexes(mean_color_pieces, mean_patch)

            if params.different_neighbors == True:
                k = 0
                index = indexes[k]
                while check_neighbors(matrice,row_index,col_index,w,h,W,H,index) == False:
                    k += 1
                    index = indexes[k]
                
            else:
                # cel mai apropiat index
                index = indexes[0]
                            
            matrice[row_index][col_index] = index  
            if params.is_grayscale == False:              
                img_mosaic[i:i+H, j:j+W, :] = (1-mask) * img_mosaic[i:i+H, j:j+W] + mask * params.small_images[index]
            else:
                img_mosaic[i:i+H, j:j+W] = (1-mask) * img_mosaic[i:i+H, j:j+W] + mask * params.small_images[index]

            col_index += 2

        row_index += 2
    
    # completez coloanele impare (1,3,5..)
    row_index = 0
    for i in range(0, image_padding.shape[0]-H, H):
        col_index = 1
        for j in range(int(2/3 * W), image_padding.shape[1] - W, int(W + 1/3 * W)):
            patch = image_padding[i:i+H, j:j+W]
            mean_patch = np.mean(patch, axis = (0,1))
            # calculez toti indicii
            indexes = get_sorted_indexes(mean_color_pieces, mean_patch)

            if params.different_neighbors == True:
                k = 0
                index = indexes[k]
                while check_neighbors(matrice,row_index,col_index,w,h,W,H,index) == False:
                    k += 1
                    index = indexes[k]

            else:
                # cel mai apropiat index
                index = indexes[0]

            matrice[row_index][col_index] = index
            if params.is_grayscale == False:    
                img_mosaic[i:i+H, j:j+W, :] = (1-mask) * img_mosaic[i:i+H, j:j+W, :] + mask * params.small_images[index]
            else:
                img_mosaic[i:i+H, j:j+W] = (1-mask) * img_mosaic[i:i+H, j:j+W] + mask * params.small_images[index]

            col_index += 2

        row_index += 2

    if params.is_grayscale ==  False:
        # elimin padding-ul
        img_mosaic = img_mosaic[H:-H, W: -W,:]
    else:
         img_mosaic = img_mosaic[H:-H, W: -W]

    end_time = timeit.default_timer()
    print('Running time: %f s.' % (end_time - start_time))
    return img_mosaic
