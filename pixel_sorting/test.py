import cv2

img=cv2.imread('baboon.png')
imge=img[:,:,1]
cv2.imshow('image',imge)
cv2.waitKey(0)
cv2.destroyAllWindows()

imge=img[:,:,2]
cv2.imshow('image',imge)
cv2.waitKey(0)
cv2.destroyAllWindows()

imge=img[:,:,3]
cv2.imshow('image',imge)
cv2.waitKey(0)
cv2.destroyAllWindows()
