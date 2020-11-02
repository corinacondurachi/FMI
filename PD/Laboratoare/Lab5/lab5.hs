-- http://www.inf.ed.ac.uk/teaching/courses/inf1/fp/

import Data.Char
import Data.List


-- 1.
rotate :: Int -> [Char] -> [Char]
rotate 0 l = l
rotate n [] = error "n este prea mare"
rotate k (xs) = if k < 0 then error "numar negativ"
                else rotate (k-1) (tail xs ++ [head xs])


-- 2. verifica functia rotate
prop_rotate :: Int -> String -> Bool
prop_rotate k str = rotate (l - m) (rotate m str) == str
                        where l = length str
                              m = if l == 0 then 0 else k `mod` l

-- 3.
alph = ['A' .. 'Z']
makeKey :: Int -> [(Char, Char)]
makeKey n = zip alph (rotate n alph)


-- 4.
lookUp :: Char -> [(Char, Char)] -> Char
lookUp c [] = c
lookUp c ((a,b):t) = if a == c then b
                     else lookUp c t

-- 5.
encipher :: Int -> Char -> Char
encipher k ch = lookUp ch (makeKey k)

-- 6.
normalize :: String -> String
litereMari = ['A' .. 'Z']
cifre = ['0' .. '9']
alfabet = litereMari ++ cifre
normalize l = filter (`elem` alfabet) (map(toUpper) (l))

-- 7.
encipherStr :: Int -> String -> String
encipherStr n l  = map (encipher n) (normalize l)

-- 8.
reverseKey :: [(Char, Char)] -> [(Char, Char)]
reverseKey [] = []
reverseKey ((a, b) : t) = ((b,a) : reverseKey t)

-- 9.
decipher :: Int -> Char -> Char
decipher n ch = lookUp ch(reverseKey (makeKey n))

decipherStr :: Int -> String -> String
decipherStr n c = map (decipher n) c
