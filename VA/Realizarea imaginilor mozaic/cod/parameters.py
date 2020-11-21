import cv2 as cv
import numpy as np

# In aceasta clasa vom stoca detalii legate de algoritm si de imaginea pe care este aplicat.
class Parameters:

    def __init__(self, image_path):
        self.image_path = image_path
        self.is_grayscale = False
        
        if self.is_grayscale == False: 
            # cititm ca rbg
            self.image = cv.imread(image_path)
        else:
            # citim ca grayscale
            self.image = cv.imread(image_path, cv.IMREAD_GRAYSCALE)
        
        if self.image is None:
            print('%s is not valid' % image_path)
            exit(-1)

        self.image = np.float32(self.image)
        self.image_resized = None
        self.small_images_dir = '/Users/corinacondurachi/Desktop/tema1/data/airplane/'
        self.image_type = 'png'
        self.num_pieces_horizontal = 25
        self.num_pieces_vertical = None
        self.show_small_images = False
        self.layout = 'caroiaj'
        self.criterion = 'distantaCuloareMedie'
        self.hexagon = False
        self.small_images = None
        self.different_neighbors = True
       