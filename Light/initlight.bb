;light
l=CreateLight(3)
RotateEntity l,-40,94,-10
AmbientLight 255, 255, 255 ;,150,150
PositionEntity l, 800,2000,12500   ;800,2000,2500


Global lr=110, lv=110, lb=120
LightColor l,lr,lv,lb
;TurnEntity l,30,0,0