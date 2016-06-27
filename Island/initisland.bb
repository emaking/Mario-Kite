;________________________________________________________geo 
;WATER
plane=CreatePlane()
EntityTexture plane,tvague2
;EntityAlpha plane,.5
;EntityOrder plane,20
 

;load terrain
terr=LoadTerrain( "_Medias/Terrain/ile_socle4.bmp" )
ScaleEntity terr,6000/TerrainSize(terr),77,3000/TerrainSize(terr)
TerrainDetail terr,4500,True
TerrainShading terr,True
PositionEntity terr,-900,-41,-500
tex=LoadTexture( "_Medias/Terrain/socletextur3.bmp",1 ) ;"sb/MossyGround.bmp",1 ) ;sb/terra.jpg",1 ) 
ScaleTexture tex,6,6
EntityTexture terr,tex
EntityType terr,GROUND
FreeTexture tex
EntityOrder terr,0
RotateEntity terr,0,50,0

 
castle=LoadMesh( "_Medias\Castle\castle1.x" )
ScaleEntity castle,.3,.3,.3
PositionEntity castle,-300,-10,1200
RotateEntity castle,0,-200,0
EntityOrder castle,0


castle2=LoadMesh( "_Medias\Castle\castle1.x" )
ScaleEntity castle2,.3,.3,.3
PositionEntity castle2,-800,0,600
RotateEntity castle2,0,90,0
EntityOrder castle2,0
 

airplane=LoadMesh( "_Medias\xfighter\biplane.x" )
ScaleEntity airplane,7,9,8	;make it more spherical!
PositionEntity airplane,100,13,550
RotateEntity airplane, -5,-40,-10
EntityOrder airplane,0
EntityType airplane, TYPEAVION

plant=CreatePivot()
LoadAnimSeq( plant, "_Medias/Sprites/Plante.b3d" )
;ScaleEntity plant,5,7,6	;make it more spherical!
PositionEntity plant,0,00,300
;PositionEntity airplane,0,00,200
;RotateEntity plant,9,130,3
 
crate=LoadMesh("_Medias\crate\wcrate1.3ds")
PositionEntity crate,-170,6,400
ScaleEntity crate,.2,.2,.2
RotateEntity crate,1,-5,3
EntityOrder crate,0
	
	 
	;load plage
plage=LoadTerrain( "_Medias/Plage/ile_playa2.bmp")  
ScaleEntity plage,4000/TerrainSize(plage),38,7000/TerrainSize(plage)
TerrainDetail plage,1500,True
TerrainShading plage,True
PositionEntity plage,-3500,-31,1000
plagetex=LoadTexture( "_Medias/Plage/sable2.bmp") 
ScaleTexture plagetex,15,35
EntityTexture plage,plagetex
EntityType plage,GROUND
EntityAlpha plage,1
EntityShininess plage,0
FreeTexture plagetex
EntityOrder plage,0
RotateEntity plage,0,-65,0
	
	
	