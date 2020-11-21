"""
    PROIECT MOZAIC
"""

# Parametrii algoritmului sunt definiti in clasa Parameters.
from parameters import *
from build_mosaic import *

# numele imaginii care va fi transformata in mozaic
#image_path = './../data/imaginiTest/ferrari.jpeg'
image_path = '/Users/corinacondurachi/Desktop/tema1/data/imaginiTest/ferrari.jpeg'
params = Parameters(image_path)

# directorul cu imagini folosite pentru realizarea mozaicului
params.small_images_dir = '/Users/corinacondurachi/Desktop/tema1/data/colectie/'
# tipul imaginilor din director
params.image_type = 'png'

# numarul de piese ale mozaicului pe orizontala
# pe verticala vor fi calcultate dinamic a.i sa se pastreze raportul
params.num_pieces_horizontal = 100
# afiseaza piesele de mozaic dupa citirea lor
params.show_small_images = False
# modul de aranjarea a pieselor mozaicului 
# optiuni: 'aleator', 'caroiaj'
params.layout = 'caroiaj'
# criteriul dupa care se realizeaza mozaicul
# optiuni: 'aleator', 'distantaCuloareMedie'5
params.criterion = 'distantaCuloareMedie'
# daca params.layout == 'caroiaj', sa se foloseasca piese hexagonale
params.hexagon = False
# daca params.is_grayscale = True, se va citi ca grayscale, daca nu ca RGB
params.is_grayscale = False
# daca params.different_neighbors = True, vom specifica ca nu vrem piese adiacente la fel
#(valabil pentru modul caroiaj cu piese dreptunghiulare, cat si cu hexagonale)
params.different_neighbors = False


params.num_pieces_horizontal = 100
img_mosaic = build_mosaic(params)
cv.imwrite('ferrari_caroiaj_distantaCuloareMedie.png', img_mosaic)
