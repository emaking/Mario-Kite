;;;;;;;;;CAMERA
Global hautcam=20, decal=20, distcam = distkiteObj + 30
Global detail=3000
Global morph=True,keycam#=6,zoom=1.5

;-------------------------------------------------
;camera-reperes : repcam
Global camera=CreateCamera()
CameraClsColor camera,100,177,247 ;110,200,255
CameraFogColor camera,40, 130, 220
CameraFogRange camera,1,25000
CameraRange camera,1,25000
CameraFogMode camera,1

 ccxInit=5
 ccyInit=5
 cczInit=5