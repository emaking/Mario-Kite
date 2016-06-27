Global info1$="The Mario Kite Demo",mode
Include "Functions/functions.bb"

Include "Starting/start.bb"
; Enable/disable antialiasing

	;;;;;;;;;FONT
	;Load fonts To a file Handle variables
	fntBeach=LoadFont("ComicSansMS",18,True,False,False)
 	fntBeachB=LoadFont("ComicSansMS",36,True,False,False)
 	fntArial=LoadFont("ComicSansMS",14,False,True,False)
 	fntArialB=LoadFont("ComicSansMS",16,True,False,True)


Dither enable
largeurE = GfxModeWidth( mode )
hauteurE = GfxModeHeight( mode )
resolE =GfxModeDepth( mode )

Global imageNumber = 0

Global sndWave 
sndWave = loadSound("_Medias/sounds/wave.mp3")

sndMusicLoop=LoadSound("_Medias/sounds/03Piste3.mp3")
SoundVolume sndMusicLoop,.3

chnMusicLoop=PlaySound(sndMusicLoop)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;ANIMATION
Const FPS=30


;;;;;;;;;GEO
;-vent
Global WWindx# = 0, WWindy# = 0, WWindz# = 10
;-mer
Global water_level = 0
Global vaguel#=400,vagueh#=150,vaguew#=145,water_reallevel# =0

Global wstart#=0,sdepth#=145,hvaginit=13
;-relief

;;;;;;;;;
;-kiteObj
Global kvit#=0,kport#=0
Const distkiteObj = 55
Const L1 = 2,L2 = 4
;-car
Global prev_x#=0,prev_y#=0,prev_z#=0,x_vel#=0,y_vel#=0,z_vel#=0,speed#=0
Const initcarx = 0, initcary = 10, initcarz = 0,speedmax#=8


;;;;;;;
Global GRAVITY#=-0.31

;;;;;;;;;COLLISIONS
Const BODY=1,TYPEAVION=2, TYPE_MOUSSE=3,WHEEL=1,WAVE=4,PLAYER=5

Const  KITE=2, PYRA=3; GROUND=10

;Collisions BODY,TYPE_MOUSSE,2,2
;Collisions WHEEL,SCENE,2,3
Collisions TYPEAVION,BODY,2,1
;Collisions WHEEL,WAVE,2,3
;Collisions BODY,WAVE,2,2



Include "Camera/initcamera.bb"

;;;;;;;;;TYPE

Type Ng
	Field nssprite,npivot
End Type

;Type Mv
;	Field vsmx#,vsmy#,vsmz#,vssprite,vstime_out,vvxl,vvlarge,vpivot
;End Type
Type Tr
	Field trsprite,trpivot,trpivot2,trpt,trh
End Type	
Type Nx
	Field nxsprite,nxpivot,nxpos,nxvelx,nxvely,nxvelz
End Type
Type Pa
	Field pasprite,papivot,papivot2,life,chinetik,width,height
End Type
Type Ra
	Field rasprite,rapivot;,rlife,rpvax# ;,pvay#,pvaz#
End Type
Type Mpa
	Field mpapivot,life,mwidth#,mheight#,mdepth#
End Type
Type Sh
	Field shpivot,shsize#,shsprite,shsprite2
End Type


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


Include "Light/initlight.bb"
Include "Waves/initwaves.bb"
Include "Island/initisland.bb"




;__________________________________________________
; Car
Global car=LoadMesh( "_Medias/Board/board_takoon.3ds" )
ScaleMesh car,0.09,0.1,0.09
PositionEntity car,-30,10,0
EntityShininess car,.2
EntityType car,BODY
EntityAlpha car,1
AlignToVector car,WWindx#,WWindy#,WWindz#,3
prev_x#=EntityX#( car )
prev_y#=EntityY#( car )
prev_z#=EntityZ#( car )
; Cartarget
cartarget=CreatePivot( car )
PositionEntity cartarget,0,0,0
EntityParent cartarget,car,True
;Wheels
Global wheels[4]
i=1
For z#=2 To -2 Step -4
	For x#=-1.5 To 1.5 Step 3
		wheels[i]=CreateSphere(8, car )
		EntityAlpha wheels[i],0
		ScaleEntity wheels[i],.5,.5,.5
		EntityRadius wheels[i],1
		EntityParent wheels[i],car,True
		PositionEntity wheels[i],x,0,z
		EntityType wheels[i],WHEEL
		i=i+1
	Next
Next

;acteurs : player
playr=LoadAnimMesh( "_Medias/Mario/mariorun.x")
ScaleEntity playr,.3,.32,.3
EntityType playr,BODY
EntityRadius playr,1.5
Animate playr,0
TurnEntity playr,-5,90,0
PositionEntity playr,EntityX(car,True),EntityY(car,True)+0.4,EntityZ(car,True)+.12
EntityParent playr,car,True
EntityShininess playr,.2
EntityAlpha playr,0.5



;;;;;;;;;;;;;;;;;;;
Global falsecar=LoadMesh( "_Medias/Board/board_takoon.3ds" )
ScaleMesh falsecar,0.09,0.1,0.09
PositionEntity falsecar,-30,10,0
EntityShininess falsecar,.2
;EntityType falsecar,BODY
AlignToVector falsecar,WWindx#,WWindy#,WWindz#,3


EntityAlpha falsecar,0.1
EntityOrder falsecar, -1



; Create barreNormal'scenery'
barreNormal=CreateCylinder( 8 )
ScaleEntity barreNormal,.19,3,.19
EntityColor barreNormal,250,200,50
TurnEntity barreNormal,0,90,90
PositionEntity barreNormal,EntityX(car,True)-1,EntityY(car,True)+2.6,EntityZ(car,True)+.12
EntityParent barreNormal,playr,True


; Create barreLeft 'scenery'
barreLeft =CreateCylinder( 8 )
ScaleEntity barreLeft ,.19,3,.19
EntityColor barreLeft ,250,200,50
TurnEntity barreLeft ,90,90,-40
PositionEntity barreLeft ,EntityX(car,True),EntityY(car,True)+2.6,EntityZ(car,True)+.12 +1.8
EntityParent barreLeft ,playr,True

; Create barreLeftC 'scenery'
barreLeftC =CreateCylinder( 8 )
ScaleEntity barreLeftC ,.19,3,.19
EntityColor barreLeftC ,250,200,50
TurnEntity barreLeftC ,90,90,0
PositionEntity barreLeftC ,EntityX(car,True)-0.8,EntityY(car,True)+2.6,EntityZ(car,True)+.12 +2
EntityParent barreLeftC ,playr,True

; Create barreLeftA 'scenery'
barreLeftA =CreateCylinder( 8 )
ScaleEntity barreLeftA ,.19,3,.19
EntityColor barreLeftA ,250,200,50
TurnEntity barreLeftA ,90,90,40
PositionEntity barreLeftA ,EntityX(car,True)-0.8,EntityY(car,True)+2.6,EntityZ(car,True)+.12 +2.2
EntityParent barreLeftA ,playr,True



; Create barreBack 'scenery'
barreBack =CreateCylinder( 8 )
ScaleEntity barreBack ,.19,3,.19
EntityColor barreBack ,250,200,50
TurnEntity barreBack ,0,90,90
PositionEntity barreBack ,EntityX(car,True)+.4,EntityY(car,True)+3,EntityZ(car,True)+.12
EntityParent barreBack ,playr,True



; Create barreRight 'scenery'
barreRight =CreateCylinder( 8 )
ScaleEntity barreRight ,.19,3,.19
EntityColor barreRight ,250,200,50
TurnEntity barreRight ,90,90,40
PositionEntity barreRight ,EntityX(car,True),EntityY(car,True)+2.6,EntityZ(car,True)+.12 - 1.8
EntityParent barreRight ,playr,True

; Create barreRightC 'scenery'
barreRightC =CreateCylinder( 8 )
ScaleEntity barreRightC ,.19,3,.19
EntityColor barreRightC ,250,200,50
TurnEntity barreRightC ,90,90,0
PositionEntity barreRightC ,EntityX(car,True)-0.8,EntityY(car,True)+2.6,EntityZ(car,True)+.12 -2
EntityParent barreRightC ,playr,True

; Create barreRightA 'scenery'
barreRightA =CreateCylinder( 8 )
ScaleEntity barreRightA ,.19,3,.19
EntityColor barreRightA ,250,200,50
TurnEntity barreRightA ,90,90,-40
PositionEntity barreRightA ,EntityX(car,True)-0.8,EntityY(car,True)+2.6,EntityZ(car,True)+.12 -2.2
EntityParent barreRightA ,playr,True


;acteurs : kiteObj

;;repcentre
repcentre=CreatePivot() 
PositionEntity repcentre,EntityX#(car,True),EntityY#(car,True),EntityZ#(car,True)

;;;;repwind
windx#= WWindx# 
windy#= WWindy# 
windz#=	WWindz#
wind# = Sqr(windx# *windx# +windy#*windy# + windz#*windz#)
repwindx#=EntityX#(car,True)+(windx# * distkiteObj / wind)
repwindy#=EntityY#(car,True)+(windy# * distkiteObj / wind)
repwindz#=EntityZ#(car,True)+(windz# * distkiteObj / wind)
repwind=CreatePivot()
PositionEntity repwind,repwindx#,repwindy#,repwindz#

;;;;windwheel	
windwheel=CreateSphere( 8,repwind )
EntityAlpha windwheel,0
ScaleEntity windwheel,.5,.5,.5
PositionEntity windwheel,0,0,0
EntityType windwheel,Water
EntityParent windwheel,repwind,True


;;;;kiteObj
kiteObj=LoadMesh( "_Medias/Kite/kite_takoon.3ds" )
TurnEntity kiteObj,180,0,0
PositionEntity kiteObj,EntityX(repwind,True),EntityY(repwind,True),EntityZ(repwind,True)
AlignToVector kiteObj,WWindx#,WWindy#,WWindz#,1
ScaleEntity kiteObj,0.65,0.65,0.65
EntityShininess kiteObj,1
EntityType kiteObj,BODY
pkiteObjc=CreatePivot()
PositionEntity pkiteObjc,EntityX(car,True),EntityY(car,True),EntityZ(car,True)
EntityParent kiteObj,pkiteObjc,True



falsekiteObj=LoadMesh( "_Medias/Kite/kite_takoon.3ds" )
TurnEntity falsekiteObj,180,0,0
PositionEntity falsekiteObj,EntityX(repwind,True),EntityY(repwind,True),EntityZ(repwind,True)
AlignToVector falsekiteObj,WWindx#,WWindy#,WWindz#,1
ScaleEntity falsekiteObj,-0.65,-0.65,-0.65
EntityShininess falsekiteObj,1


ombrekiteObj=LoadMesh( "_Medias/Kite/kite_takoon.3ds" )
ScaleEntity ombrekiteObj,0.55,0.55,0.55

	
EntityColor ombrekiteObj, 100,100,100

ombre_tex=LoadTexture("_Medias/Sprites/fumee.bmp",12)
EntityTexture ombrekiteObj,ombre_tex

EntityOrder ombrekiteObj, -1

EntityAlpha ombrekiteObj,0.05
EntityShininess ombrekiteObj,1

;EntityType falsekiteObj,KITE
falsepkiteObjc=CreatePivot()
PositionEntity falsepkiteObjc,EntityX(falsecar,True),EntityY(falsecar,True),EntityZ(falsecar,True)
EntityParent falsekiteObj,falsepkiteObjc,True

EntityAlpha falsekiteObj,0.1
EntityOrder falsekiteObj, -1


;;?
d_dis# = 0:v_dis#=EntityDistance#(kiteObj,repwind)





;time
period=1000/FPS
time=MilliSecs()-period


TurnEntity pkiteObjc,70,0,0
	
FlushKeys 


vitesse# = 0.1

Global resollarge#=15,paslarge#=85,whauteurinit#=20,woffset#=0,planning#=0.2,kspeed#=1
Global pasxl#= (p7x#-pax#),resolxl#=8,nbpoint#=7
Global hwauteur#=0,k#=0
wp =CreatePivot()
PositionEntity wp,0,0,0

Global ter$=terr,pla$=plage,rec$=recif
Global transla# = 0,wnorm#=-15, wpic#=-7, wmouss#=-4,cassur=0,hlresult#=0,mhlresult#=0
Global shattack#=0


moussmodel= CreateSphere(8)

EntityAlpha moussmodel,1
EntityShininess moussmodel,1

transla# = -0.5*resolxl*pasxl#
Global ultracompt=0
PositionTexture tvague,0,0

alphafond#=1



Global arbre_sprite,tree_sprite,moussmod,groupie_sprite,nuage_sprite,tree_sprite2,coco_sprite,trnx=0,groupie2_sprite,wparticule_sprite,particule_sprite,particule_mousse,particulep_sprite,shark_sprite,shark_sprite2

Global mousstextr=LoadTexture( "./_Medias/Mousse/moussetextur.bmp",12)
ScaleTexture mousstextr,1,1
Global testrelief=0

PositionEntity car,50,5,0
Global blob$="non",sk1#=1,recifpa=0
Global ncomptyaw#=EntityYaw#(car,True)
If ncomptyaw#<0 ncomptyaw#=360+ncomptyaw#
vcomptyaw#=ncomptyaw#
tcomptyaw#=0
Global comptsrprite=0


Global pause=0, angleBarre=0

soleil=LoadSprite("_Medias/Sky/soleil.png",2)
ScaleSprite soleil,200,200
EntityAlpha soleil,1
SpriteViewMode soleil,3
PositionEntity soleil,800,2000,12500

nuage=LoadSprite("_Medias/Sky/nuage.png",2)
ScaleSprite nuage,1500,250
EntityAlpha nuage,.9
SpriteViewMode nuage,3
PositionEntity nuage,1500,600,3000

nuage1=LoadSprite("_Medias/Sky/nuage.png",2)
ScaleSprite nuage1,400,150
EntityAlpha nuage1,.9
SpriteViewMode nuage1,3
PositionEntity nuage1,150,800,2000

;texsoleil=LoadTexture("_Medias/Sky/sky2.bmp",1)
;ScaleTexture texsoleil,100,100
;EntityTexture soleil, texsoleil
	
setup()
loadenviron()
EntityAlpha moussmod,0.8
foix=0
time0=MilliSecs()

	sky=LoadSkySphere()
	;sky=LoadSkyBox("_Medias/Sky/")
	PositionEntity sky, 0,90,0
	EntityAlpha sky,0
	EntityOrder sky,40
	
	
	;;;
	
	MoveEntity car,-45,0,130 ;
	
	TurnEntity pkiteObjc,0,200,0;

	x_vel#=0:prev_x#=EntityX#( car ):y_vel#=0:prev_y#=EntityY#( car ):z_vel#=0:prev_z#=EntityZ#( car )	
	TurnEntity car,0,-70,0;
;_______________________________________________WHILE__________________________________________________;
 
	
frameTimer=CreateTimer(25)


While Not KeyHit(1)


WaitTimer(frameTimer) ; 




	strmess$=""
	
	
	foix=foix+1
	If foix>11 time1=MilliSecs():timet=time1-time0:time0=time1
	;vent
;	WWindz# =13 + Rnd#(0,1)

	;EntityColor fond,CRed,CGreen,CBlue
;If (tcomptyaw#+90-EntityYaw#(pkiteObjc,True))<-270 tcomptyaw#=tcomptyaw#+270
;If (tcomptyaw#+90-EntityYaw#(pkiteObjc,True))>270 tcomptyaw#=tcomptyaw#-270
;If ttouryaw#>360 ttouryaw#=ttouryaw#-360:tournicoti=tournicoti+1:vcomptyaw#=EntityYaw#(car,True)+90
;If ttouryaw#<-360 ttouryaw#=ttouryaw#+360:tournicoti=tournicoti+1:vcomptyaw#=EntityYaw#(car,True)+90


	
	;ncomptyaw#=EntityYaw#(car,True)
	
	
	;If ncomptyaw#<0 ncomptyaw#=360+ncomptyaw#
	;tmp#=(ncomptyaw#-vcomptyaw#)
	;If tmp#<-180  
	;	tmp#=tmp#+360
	;Else If tmp#>180
	;	tmp#=tmp#-360
	;End If
	;tcomptyaw# = tcomptyaw#+tmp#
	;vcomptyaw#= ncomptyaw#
	
	;ttouryaw#=(tcomptyaw#+90-EntityYaw#(pkiteObjc,True))

	;If ecumx#=0 And ttouryaw#<=-180 TurnEntity car,0,-180-ttouryaw#,0;:tcomptyaw#=tcomptyaw#-180-(tcomptyaw#+90-EntityYaw#(pkiteObjc,True))
	;If ecumx#=0 And ttouryaw#>=180 TurnEntity car,0,180-ttouryaw#,0;:tcomptyaw#=tcomptyaw#+180-(tcomptyaw#+90-EntityYaw#(pkiteObjc,True))
	
	
	
	
	
	; Enable/disable hardware dithering
	;Dither enable
	AntiAlias enable
	time=time+period
	
	


	;;;;;;;;;;;;;;;
	phc#=MilliSecs()/5
	
	
PositionEntity sky,EntityX(camera,True)-2500,-20,EntityZ(camera,True)+6000
	
			
			
	If(pause=0)
		strmess$=""
		
		
		Include "Waves/updatewaves.bb"
		Include "Mario/updatemario.bb"
		Include "Sprites/updatesprites.bb"
		
		EntityAlpha mesh, 1 ; alpha water 


		
	Else If(pause=1)
		 
		Include "Light/updatelight.bb"
		
		If KeyDown(16) ; Q
			alphafond# =alphafond# - 0.05
			EntityAlpha mesh, alphafond#
 
		Else If KeyDown(17) ; W
			alphafond# =alphafond# + 0.05
			EntityAlpha mesh, alphafond#
			
		End If
		
 
If KeyDown(78) MoveEntity car,0,1,3:x_vel#=0:prev_x#=EntityX#( car ):y_vel#=0:prev_y#=EntityY#( car ):z_vel#=0:prev_z#=EntityZ#( car )

If KeyDown(73) TurnEntity pkiteObjc,0,0,-12 ;kiteObjr

If KeyDown(71) TurnEntity pkiteObjc,0,0,12;kiteObjr


If KeyDown(77) And (EntityRoll# (car,True) < 90)  TurnEntity car, -10/(speed),-10,-10*Sgn(speed) 
If KeyDown(75) And (EntityRoll# (car,True) > -90)  TurnEntity car,-10/(speed),10,10*Sgn(speed) 
If KeyDown(81) And (EntityRoll# (car,True) < 100)  TurnEntity car, -20/(speed),-20,-20*Sgn(speed) 
If KeyDown(79) And (EntityRoll# (car,True) > -100)  TurnEntity car,-20/(speed),20,20*Sgn(speed) 
	
	End If
	


	
;	PositionEntity ombrekiteObj,EntityX(kiteObj,True)-50,-4,EntityZ(kiteObj,True)+20
;	RotateEntity ombrekiteObj,EntityPitch#(kiteObj,True),EntityYaw#(kiteObj,True),EntityRoll#(kiteObj,True)
	


	
	;_______________________________________________________________renderworld	
	UpdateWorld
	RenderWorld	
	

Include "Camera/updatecamera.bb"
Include "Starting/updatedisplay.bb"

	;	dbug$="time="+time+" phc#="+phc#
	;	SetFont fntArialB
	;	Text largeurE/2,(HauteurE*3/4)+(HauteurE/4)*1.5/5, dbug$,True,False
		
		
	;____________________________________________________releases


 
 If KeyHit(88) 
imageNumber = imageNumber +1

fileName = "MarioKite-"+imageNumber+".bmp"

	SaveBuffer(FrontBuffer(),fileName )
End If




If KeyDown(59) pause=pause+1

If(pause>1)pause=0;
	
	
If(pause=0)

	FreeEntity mesh

	
	
	FreeEntity ppwind
		
	For rr.Ra=Each RA	
		FreeEntity rr\rapivot
		Delete rr
	Next
End If

	


	Flip
	
Wend

	
End










			