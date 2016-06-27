;____________________________________________________update camera
	
	If KeyDown(61) hautcam = hautcam -7
	If KeyDown(62) hautcam = hautcam +8
	
	If KeyDown(63) distcam = distcam +8
	If KeyDown(64) distcam = distcam -7
	
	If KeyDown(65) decal = decal +8
	If KeyDown(66) decal = decal -7
	
	windx#= WWindx# + x_vel#*.4
	windy#= WWindy# + y_vel#*.6
	windz#=	WWindz# + z_vel#*.6
	wind# = Sqr((windx# *windx#) + (windy#*windy#) + (windz#*windz#))
	
	repcamx#=(EntityX#(car,True)-(windx# * distcam / wind)*3)+ decal 
	repcamy#=( hautcam+EntityY#(car,True))
	repcamz#=(EntityZ#(car,True)-(windz# * distcam / wind)*2) 
	PositionEntity camera,repcamx , repcamy ,repcamz
	
	ccx=ccxInit+((EntityX#(kiteObj,True)-EntityX#(car,True))/2) ;- 15*Sin(EntityYaw(pkiteObjc,True))
	ccy=ccyInit+((EntityY#(kiteObj,True)-EntityY#(car,True))/5.5) 
	ccz=cczInit+(EntityZ#(car,True))/1 

	
	If KeyDown(2) ccxInit = ccxInit +8
	If KeyDown(3) ccxInit = ccxInit -7
	If KeyDown(4) ccyInit = ccyInit +8
	If KeyDown(5) ccyInit = ccyInit -7
	If KeyDown(6) cczInit = cczInit +8
	If KeyDown(7) cczInit = cczInit -7
	
	camtarget=CreatePivot()
	PositionEntity camtarget, ccx+EntityX#(car,True), (ccy+ EntityY#(car,True)), ccz

	CameraZoom camera,zoom
	PointEntity camera,camtarget,0

FreeEntity camtarget