

;;______________________________functions_____________________________________________________________________ 

;; hideAllBars
Function hideAllBars()
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	
	

End Function

	
;;FUNCTION GETPLANING
Function getplaning#(tspeed#)
	If Abs(tspeed#)>= 0.3  
		tplaning#=0
	Else
		tplaning# = -1.5 + 1.5 *(Sin(tspeed#*90/(0.4)))  ;speedmax#*
	End If
	Return tplaning#	
End Function

;;FUNCTION GETLVL#		
Function getlvl#(name$)

	plvl# =  TerrainY#(pla$,EntityX#(name$,True),0,EntityZ#(name$,True)) ;plage
	tlvl# =  TerrainY#(ter$,EntityX#(name$,True),0,EntityZ#(name$,True)) ;terr	
	result# = plvl#
	If (tlvl# > result#) result#=tlvl#  ;tlvl#+0.4
	Return result#
End Function		
		
;;FUNCTION GETH#  coef de hauteur wave : 0< hlresult< 1.25 //// 0< mhlresult< 1
Function geth#(whfx#,whfz#) 
	If (whfz#>50) And (whfz#<2500) And (whfx#>-2700) And (whfx#<-500) 
		hlresult# = 0  
		mhlresult# = 0 
		Return
	End If
	ph# =  TerrainY#(pla$,whfx#,0,whfz#) ;plage
	th# =  TerrainY#(ter$,whfx#,0,whfz#) ;terr		
	plh# = ph#
	If (th# > plh#) plh#=th#

	If plh# <=  wnorm#  				;<norm
			hlresult# =  ( wnorm#/plh#) 
			mhlresult# = 0
			Return
	Else If plh# <=  wpic#				;<pic
			hlresult# =  (0.75*( wnorm# -plh# )/(wnorm#-wpic#) +.5)
			mhlresult# = 0
			Return
	Else If plh# <  wmouss# 			;<mouss
			hlresult# =  (-(plh#-wpic# )/(wmouss#-wpic#) +1.25)
			mhlresult# =  ((plh#-wpic# )/(wmouss#-wpic#))
			Return
	Else If plh# <  -0.2					;>mouss
			hlresult# =  (0.25*(wmouss# -plh# )/(-wmouss#) +0.26)
			mhlresult# =  ((wmouss# -plh# )/(-wmouss#)  +1.1)
			Return
	Else 
			hlresult# =  0 
			mhlresult# =  0
			Return
	End If
	Return 
End Function
		
		


;;;FUNCTION GET COURBE    courbes de la vague
Function getcourbey#(gcx#) 
	If gcx# <= p0x#  
		Return (k0# * gcx# + cte0#	)*0.5
	Else If gcx# <= p1x#  	;p2
		Return (k1# * gcx# + cte1#	)*0.5	
	Else If gcx# <= p2x#  	;p2
		Return (k2# * gcx# + cte2#	)*0.5		
	Else If gcx# <= p3x#  ;p3
		Return (k3# * gcx# + cte3#	)*0.5	
	Else If gcx# <= p4x# ;p4
		Return (k4# * gcx# + cte4#	)*0.5	
	Else If gcx# <= p5x#  ;p5	
		Return (k5# * gcx# + cte5#	)*0.5
	Else If gcx# <= p6x#  ;p6
		Return (k6# * gcx# + cte6#	)*0.5
	Else If gcx# <= p7x#  ;p7
		Return (k7# * gcx# + cte7#	)*0.5
	EndIf
End Function
		
				 
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;LOAD ENVIRON;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;		
Function LoadEnviron()
	;arbre
	

	;tree 
	For k=1 To 1000
		Repeat
			tx#=Rnd(-100,5500)
			tz#=Rnd(700,3000)
			ty1#=TerrainY( ter$,tx#,0,tz# )
			ty2#=TerrainY( pla$,tx#,0,tz# )
			If ty1#>ty2# 
				ty#=ty1#
			Else 
				ty#=ty2#
			End If
		Until ty>1
		
		hazard#=Rnd#(-55,5)
		
		
		If ty >3 And ty <6 And hazard<-30 
			
			
				t =	CopyEntity(groupie_sprite)
				ScaleSprite t,1.9*2.7,2.7
				PositionEntity t,tx,ty+2.5,tz
				
				
		Else If hazard<0
			arbre=CopyEntity(arbre_sprite)
			PositionEntity arbre,tx#,ty#-5.5,tz#
			  
			ScaleSprite arbre,Rand(15,25)*ty/20  ,Rand(15,20)*ty/30
			poseToGround(arbre)

			
		;	arbre1=CopyEntity(arbre_sprite)
		;	PositionEntity arbre1,tx#+Rnd#(-2,2),ty#-Rnd#(-4,-6),tz#+Rnd#(-2,2)
		;	poseToGround(arbre1)

			
		;	arbre2=CopyEntity(arbre_sprite)
		;	PositionEntity arbre2,tx#+Rnd#(-2,2),ty#-Rnd#(-4,-6),tz#+Rnd#(-2,2)
		;	poseToGround(arbre2)

			
		;	arbre3=CopyEntity(arbre_sprite)
		;	PositionEntity arbre3,tx#+Rnd#(-2,2),ty#-Rnd#(-4,-6),tz#+Rnd#(-2,2)
		;	poseToGround(arbre3)


		Else  
 
				tay=Rand(7,16) ;+ty-5
				CreateTree(tx,ty,tz,tay)
			
 
 
				
		 
		End If
		
		If ty >500 
			tay=Rand(7,9) ;+ty-5
			CreateTree(tx,ty,tz,tay)
			
			arbre1=CopyEntity(arbre_sprite)
			PositionEntity arbre1,tx#+Rnd#(-5,5),ty#-Rnd#(-4,-6),tz#+Rnd#(-5,5)
			PositionEntity arbre1,EntityX(arbre1, True), getlvl#(arbre1), EntityZ(arbre1, True)
			ScaleSprite arbre1,4,1.3
			poseToGround(arbre1)

			
			arbre2=CopyEntity(arbre_sprite)
			PositionEntity arbre2,tx#+Rnd#(-5,5),ty#-Rnd#(-4,-6),tz#+Rnd#(-5,5)
			ScaleSprite arbre2,7,1.5
			poseToGround(arbre2)
			
			arbre3=CopyEntity(arbre_sprite)
			PositionEntity arbre3,tx#+Rnd#(-5,5),ty#-Rnd#(-4,-6),tz#+Rnd#(-5,5)
			ScaleSprite arbre3,5,1
			poseToGround(arbre3)
			
			arbre4=CopyEntity(arbre_sprite)
			PositionEntity arbre4,tx#+Rnd#(-5,5),ty#-Rnd#(-4,-6),tz#+Rnd#(-5,5)
			ScaleSprite arbre4,12,2.1
			poseToGround(arbre4)
			
			arbre5=CopyEntity(arbre_sprite)
			PositionEntity arbre5,tx#+Rnd#(-5,5),ty#-Rnd#(-4,-6),tz#+Rnd#(-5,5)
			ScaleSprite arbre5,10,2.4
			poseToGround(arbre5)
			
			arbre6=CopyEntity(arbre_sprite)
			PositionEntity arbre6,tx#+Rnd#(-5,5),ty#-Rnd#(-4,-6),tz#+Rnd#(-5,5)
			ScaleSprite arbre6,7,1.9
			poseToGround(arbre6)
			
		End If	
		
		
		If ty >110		
			tay=Rand(9,13) ;+ty-12		
		;	CreateTree(tx,ty,tz,tay)
			
			arbre1=CopyEntity(arbre_sprite)
			PositionEntity arbre1,tx#+Rnd#(-17,17),ty#-Rnd#(-4,-6),tz#+Rnd#(-17,17)
			ScaleSprite arbre1,9,3
			poseToGround(arbre1)


			
			arbre2=CopyEntity(arbre_sprite)
			PositionEntity arbre2,tx#+Rnd#(-17,17),ty#-Rnd#(-4,-6),tz#+Rnd#(-17,17)
			ScaleSprite arbre2,12,4
			poseToGround(arbre2)
			
			arbre3=CopyEntity(arbre_sprite)
			PositionEntity arbre3,tx#+Rnd#(-17,17),ty#-Rnd#(-4,-6),tz#+Rnd#(-17,17)
			ScaleSprite arbre3,15,5
			poseToGround(arbre3)
			
			arbre4=CopyEntity(arbre_sprite)
			PositionEntity arbre4,tx#+Rnd#(-17,17),ty#-Rnd#(-4,-6),tz#+Rnd#(-17,17)
			poseToGround(arbre4)
			
			arbre5=CopyEntity(arbre_sprite)
			PositionEntity arbre5,tx#+Rnd#(-17,17),ty#-Rnd#(-4,-6),tz#+Rnd#(-17,17)
			ScaleSprite arbre5,18,6
			poseToGround(arbre5)
			
			arbre6=CopyEntity(arbre_sprite)
			PositionEntity arbre6,tx#+Rnd#(-17,17),ty#-Rnd#(-4,-6),tz#+Rnd#(-17,17)
			ScaleSprite arbre6,21,7
			poseToGround(arbre6)
			
		End If	
	Next
	
	;nuages
	;For k=1 To 10
	;	CreateNg()	
	;Next
	
	;sharks
	For k=1 To 250
		Repeat
			tx#=EntityX(car,True)+Rnd#(0,4000)
			tz#=EntityZ(car,True) +Rnd#(0,3000)
			ty1#=TerrainY( ter$,tx#,0,tz# )
			ty2#=TerrainY( pla$,tx#,0,tz# )
			If ty1#>ty2# 
				ty#=ty1#
			Else 
				ty#=ty2#
			End If
		Until ty<-1
		CreateShark(tx#,tz#)	
	Next
End Function

Function poseToGround(theObject)
	PositionEntity theObject,EntityX(theObject, True),getlvl#(theObject),EntityZ(theObject, True)


End Function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;SHARKS;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function CreateShark.Sh(tx,tz)
	ss.Sh= New Sh
	ss\shsize#=Rnd(1,2.5)
	ss\shpivot=CreatePivot()	
	ss\shsprite= CopyEntity( shark_sprite,ss\shpivot)
	ss\shsprite2= CopyEntity( shark_sprite2,ss\shpivot)
	PositionEntity ss\shpivot,tx,0,tz
	ScaleSprite ss\shsprite,-ss\shsize*8,ss\shsize*2.5
	ScaleSprite ss\shsprite2,ss\shsize*8,ss\shsize*2.5
	EntityShininess ss\shsprite,1
	;EntityOrder ss\shsprite,-1
	EntityAlpha ss\shsprite,1
	Return ss
End Function

Function UpdateShark.Sh(ss.sh)
	;If EntityDistance(ss\shpivot,car)>500  Return 
	
	nxdis#=EntityDistance#(ss\shpivot,car)
	shattack#=0
	
	If nxdis# <6;; attack
		speed#=0.02*speed#
		shattack=Rnd#(-8,-5)
		sharkspeed#=sharkspeed#+0.5
		TurnEntity car,Rnd(0,5),Rnd(-5,5),Rnd(-5,5)
		scorevie=scorevie-10
		createPa(2,80,3,7,6,EntityX#(car,True),EntityY#(car,True)+0.05,EntityZ#(car,True))
		chillset#=-2
	Else If nxdis# < 150 ;; hunt
		PointEntity ss\shpivot,car
		TurnEntity ss\shpivot,0,90+Rnd#(-5,5),0
		sharkspeed#=1.2 +Rnd#(0.2,0.4)
	Else  ;;chill out
		TurnEntity ss\shpivot,0,Rnd(-10,10),0
		TranslateEntity ss\shpivot,0,3.5,0
		sharkspeed#=.4 +Rnd#(0,0.4)
		chillset#=-1
	End If
	PositionEntity ss\shpivot,EntityX#(ss\shpivot,True),-6+chillset#,EntityZ#(ss\shpivot,True)
	MoveEntity ss\shpivot,sharkspeed#,0,0 
	If  getlvl#(ss\shpivot)>-1
		MoveEntity ss\shpivot,-sharkspeed#,0,0   
		TurnEntity ss\shpivot,0,Rnd(-20,20),0
	End If
	
	
	;TurnEntity ss\shpivot,-EntityPitch(ss\shpivot,True),0,0
	EntityAlpha ss\shsprite,Sgn(EntityYaw#(ss\shpivot,True)+180)
	EntityAlpha ss\shsprite2,Sgn(EntityYaw#(ss\shpivot,True)+180)
	
	Return ss
End Function		
		
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;TREES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function CreateTree.Tr(tx,ty,tz,tay)
	tt.Tr= New Tr	
	tt\trpivot= CopyEntity( tree_sprite )
	tt\trpivot2= CopyEntity( tree_sprite2)
	PositionEntity tt\trpivot,tx,ty-4,tz
	PositionEntity tt\trpivot2,tx,ty-4,tz
	ScaleSprite tt\trpivot,tay*2.7,tay*4.5-ty+13
	ScaleSprite tt\trpivot2,tay*2.7,tay*4.5-ty+13		
	tt\trh =tay*3.6-ty+13
	EntityOrder tt\trpivot,0
	EntityOrder tt\trpivot2,0
	Return tt
End Function

Function UpdateTree.Tr(tt.tr)
	If  EntityDistance(tt\trpivot,camera)>200 Return  ;EntityInView(tt\trpivot,camera)=False Or Rnd(-3,3)>0
	;tt\trpt =Abs(Cos(MilliSecs()*120 + Rnd(0,90)))
	;EntityAlpha tt\trpivot,tt\trpt
	;EntityAlpha	tt\trpivot2,1-tt\trpt
	
	tt\trpt =Rnd(-1,1)
	If(tt\trpt<=0) HideEntity tt\trpivot:ShowEntity tt\trpivot2
	
	If(tt\trpt>=0) HideEntity tt\trpivot2:ShowEntity tt\trpivot
	
	nxdis=Sqr( (EntityX(car,True)-EntityX(tt\trpivot,True))^2 + (EntityZ(car,True)-EntityZ(tt\trpivot,True))^2 )
	If nxdis < 3
		MoveEntity car,-cderapage,0.5,-speed
		scorevie=scorevie-5
		CreateNx(EntityX(tt\trpivot,True),(EntityY(tt\trpivot,True)+2*tt\trh),EntityZ(tt\trpivot,True))
		Return tt
	Else If nxdis < 100 And trnx <2 And Rnd(-3,3)>0
		trnx = trnx+1
		CreateNx(EntityX(tt\trpivot,True),(EntityY(tt\trpivot,True)+2*tt\trh),EntityZ(tt\trpivot,True))
		Return tt
	End If
	;ShowEntity tt\trpivot
	;EntityOrder tt\trpivot,-1
	Return tt
End Function


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;NUAGES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;		
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;NUAGES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;		
Function CreateNg.Ng()
	
	nn.Ng= New Ng	
	nn\nssprite=CreatePivot()
	nn\npivot= CopyEntity( nuage_sprite,nn\nssprite )
	PositionEntity nn\nssprite,500*Rnd#(-50,50),Rnd(200,700),Rnd(5500,6000),True
	ScaleSprite nn\npivot,1000*Rnd#(-4,4)+1000,20*Rnd#(2,4)
	EntityAlpha nn\npivot,Rnd#(0.7,0.9)
	Return nn
End Function			
		
Function UpdateNg(nn.Ng)
	If EntityInView(nn\nssprite,camera)=False Return		
	TranslateEntity nn\nssprite,-3*WINDX#,-3*WINDY#,-3*WINDZ#		
	If (EntityX(nn\nssprite,True)< -10000) Or (EntityX(nn\nssprite,True)> 10000) Or (EntityZ(nn\nssprite,True)< 4000) Or (EntityZ(nn\nssprite,True)> 7000) Then
		FreeEntity nn\nssprite
		Delete nn
		CreateNg
		Return	
	End If
End Function


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;NOIX;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function CreateNx.Nx(tmpx#,tmpy#,tmpz#)		
	xx.Nx= New Nx
	xx\nxvelx#=(EntityX#(car,True)+x_vel#-tmpx#)*0.05
	xx\nxvely#=6+Rnd#(0.5,2.5)
	xx\nxvelz#=(EntityZ#(car,True)+z_vel#-tmpz#)*0.05
	xx\nxpos=60
	xx\nxpivot = CreatePivot()
	xx\nxsprite=CopyEntity( coco_sprite,xx\nxpivot )
	PositionEntity xx\nxpivot,tmpx#,tmpy#,tmpz#
	;PointEntity xx\nxpivot,car
	;RotateEntity xx\nxpivot,.5*EntityPitch(xx\nxpivot,True),EntityYaw(xx\nxpivot,True),0
	;TurnEntity xx\nxpivot,Rnd#(-1,10),Rnd#(-1,1),0
	Return xx
End Function

Function UpdateNx(xx.Nx)
	tmpy#=EntityY#(xx\nxsprite,True)
	If EntityInView(xx\nxsprite,camera)=False ;xx\nxpos<0 ;
		
		FreeEntity xx\nxpivot
		Delete xx
		Return
	Else If xx\nxpos<60
		If xx\nxpos < 20 trnx=trnx-1
		If trnx <0 trnx=0
		xx\nxpos=xx\nxpos-1	
		Return
	End If 
	xx\nxvely#=xx\nxvely#+GRAVITY#
	TranslateEntity xx\nxpivot,xx\nxvelx#,xx\nxvely#,xx\nxvelz#,True
	;MoveEntity xx\nxpivot,0,0,1.5
	;TranslateEntity xx\nxpivot,0,GRAVITY#*2,0
	tmpy#=EntityY#(xx\nxsprite,True)
	nxtmph#=getlvl#(xx\nxpivot)
	nxdis#=Sqr( (EntityX#(car,True)-EntityX#(xx\nxsprite,True))^2 + (EntityZ#(car,True)-EntityZ#(xx\nxsprite,True))^2 )
			
	If (EntityY#(xx\nxpivot,True)< nxtmph#+2)
		MoveEntity xx\nxpivot,0,0,-1.5
		TranslateEntity xx\nxpivot,0,nxtmph#-tmpy#,0 
		xx\nxpos=xx\nxpos-1
		Return		
	Else If nxdis# < 1 
		TranslateEntity xx\nxpivot,0,nxtmph#-tmpy#,0
		MoveEntity xx\nxpivot,0,0,1.5
		PositionEntity car,EntityX#(xx\nxsprite,True),EntityY#(xx\nxsprite,True),EntityZ#(xx\nxsprite,True),True
		MoveEntity xx\nxpivot,0,0,-1.5	
		xx\nxpos=xx\nxpos-1
		scorevie=scorevie-5
		Return
	End If
					
	Return	
End Function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;GERBE PARTICULES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;		
Function CreatePa.Pa(c,w,chinetik#,width#,height#,x#,y#,z#)
	
	pp.Pa= New Pa	
	pp\life=w
	pp\chinetik=chinetik#
	pp\width =width#
	pp\height=height#
	pp\pasprite=CreatePivot()
	;If w=1
		pp\papivot= CopyEntity(particule_sprite,pp\pasprite )
		PositionEntity pp\pasprite,x#,y#,z#,True
		EntityAlpha pp\papivot,1 ;0.6+Rnd#(-0.3,0.7)
	;	pp\life=30
	;Else
	;	pp\papivot= CopyEntity(particule_sprite,pp\pasprite )
	;	pp\life=2
	;	PositionEntity pp\papivot,x#,y#,z#,True
	;	EntityAlpha pp\papivot,1
	;End If
	

	tmp#=Rnd(-10,50)
	If c=2 
		EntityColor pp\papivot,250+tmp#,+tmp#,+tmp#
		
	Else If c=1 
		EntityColor pp\papivot,226+tmp#,186+tmp#,158+tmp#
	End If
	Return pp
End Function			
		
Function UpdatePa(pp.Pa)
;Abs(EntityX#(pp\papivot,True)-EntityX#(car,True))>500
	If  EntityInView(pp\papivot,camera)=False Or pp\life<0 Or(EntityZ#(pp\papivot,True)-EntityZ#(car,True))<-50 Or (EntityZ#(pp\papivot,True)-EntityZ#(car,True))>800
		FreeEntity pp\pasprite
		Delete pp
		Return
	End If
	pp\width =pp\width *.95
	pp\height=pp\height *.8
	If pp\height <.05 pp\height=.05
	palvl#=getalt#(pp\pasprite,0)
	If EntityY#(pp\pasprite,True)<=(palvl#)	
		pp\height=pp\height *.8
		EntityAlpha pp\papivot,0.9+Rnd#(-0.6,0.1) 
		PositionEntity pp\pasprite,EntityX#(pp\pasprite,True),palvl#,EntityZ#(pp\pasprite,True),True
		ScaleSprite pp\papivot,pp\width,pp\height
		pp\life=pp\life-Rnd(1,2)
		pp\chinetik=0
	;	If pp\life <0
	;		FreeEntity pp\pasprite
	;		Delete pp
	;	End If
		Return
	Else
		EntityAlpha pp\papivot,1 
		pp\chinetik=pp\chinetik+(GRAVITY#)*2	
		TranslateEntity pp\pasprite,0,pp\chinetik,0 
		ScaleSprite pp\papivot,pp\width,pp\height
		Return
	End If	
End Function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;ROULEAU PARTICULES;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;		
Function CreateRa.Ra(rw#,rh#,x#,y#,z#)
	rr.Ra= New Ra
	rr\rapivot=CreatePivot()
	rr\rasprite= CopyEntity( particulep_sprite,rr\rapivot )
	PositionEntity rr\rapivot,x#,y#,z#,True
	ScaleSprite rr\rasprite,rw#,rh#
	Return rr
End Function			
		
Function UpdateRa(rr.Ra)

	
;	If rr\rlife<0 Or EntityInView(rr\rapivot,camera)=False
;			FreeEntity rr\rasprite
;			Delete rr
;			Return
;	Else 
;		rr\rlife=rr\rlife-1
	;	rr\rpvax#= rr\rpvax#+Rnd#(0.1,0.3)
;		EntityAlpha rr\rasprite,Rnd#(.6,.9)
;		palvl#=getalt#(rr\rapivot,0)
;		PositionEntity rr\rapivot,EntityX#(rr\rapivot,True),palvl#+Rnd#(-.01,.2),EntityZ#(rr\rapivot,True),True
	;	ScaleSprite rr\rasprite,rr\rpvax#*8,rr\rpvax#*70
	;	RotateEntity rr\rapivot,Rnd#(-1,1),0,Rnd#(-1,1)
;		Return
;	End If
	
	
	;If EntityY#(rr\rapivot,True)<=(palvl#)		 
	;		EntityAlpha rr\rasprite,Rnd#(0,.9) ;(pp\life/10)
	;		PositionEntity rr\rapivot,EntityX#(rr\rapivot,True),palvl#,EntityZ#(rr\rapivot,True),True
	;		rr\life=rr\life-1
	;		rr\pvax#=rr\pvax#*Rnd#(0.85,0.999)
	;		ScaleSprite rr\rasprite,rr\pvax#*2.5,rr\pvax#
	;		Return
	;Else If rr\life<10
	;	EntityAlpha rr\rasprite,Rnd#(0,.9) ;(pp\life/10)
	;	PositionEntity rr\rapivot,EntityX#(rr\rapivot,True),palvl#,EntityZ#(rr\rapivot,True),True
	;	rr\life=rr\life-1
	;	rr\pvax#=rr\pvax#*Rnd#(0.85,0.999)
	;	ScaleSprite rr\rasprite,rr\pvax#*2,rr\pvax#
	;	Return
	;Else
	;	EntityAlpha rr\rasprite,1
	;	rr\pvay#=rr\pvay#+(GRAVITY#)+Rnd#(0,0.3)	
	;	TranslateEntity rr\rapivot,0,rr\pvay#,0 
	;	rr\pvax#=rr\pvax#+Rnd#(0.15,0.5)	
	;	ScaleSprite rr\rasprite,rr\pvax#*2,rr\pvax#
	;	Return
	;End If	
End Function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;ROULEAU MOUSSE;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	
Global sndDist=100, sndVol=1, chnWave=0, sndPan = 0
Function playWaveSound()
	if ChannelPlaying(chnWave) Return
	
	SoundVolume sndWave, sndVol * 0.1
	SoundPan sndWave, sndPan * 0.5
	chnWave = PlaySound(sndWave)
	
End function

Function createWaveSound(dist#, vol#)
	if sndIsPlaying = 1 Return
	if dist# < sndDist 
		playWaveSound()
	
	end if
End Function
	
Function CreateMpa.Mpa(mpvax#,mpvay#,mpvaz#,tcoef#)
	If mpvaz#<(EntityZ#(camera,True)-500) Or (mpvaz#-EntityZ#(car,True))>2000 Or tcoef#<0.1 Return 
	
	mdepth#=80*tcoef#
	nxdis#=	(mpvaz#-(mdepth#*0.5)-EntityZ#(car,True))

		mpp.Mpa= New Mpa
		mpp\mwidth#=30*tcoef#
		mpp\mheight#=13*tcoef#
		mpp\mdepth#=80*tcoef#
		mpp\life=800
		mpp\mpapivot= CopyEntity(moussmod)
		EntityAlpha mpp\mpapivot,0.9
		ScaleEntity mpp\mpapivot,mpp\mwidth#,mpp\mheight#,mpp\mdepth#
		PositionEntity mpp\mpapivot,mpvax#-(mpp\mwidth#*0.5),mpvay#-(mpp\mheight#*0.85)+Rnd#(-0.15,0.15),mpvaz#-(mpp\mdepth#*0.5),True
		
		
		
		if nxdis# < 100 and  nxdis# > 0  
			sndVol =  mpp\mheight# / 14 ;mpp\life / 1600 
			sndpan = nxdis# / 150
			playWaveSound()
			
		else if nxdis# < 0 and  nxdis# > -100 
			sndVol =  mpp\mheight# / 14 ;mpp\life / 1600 
			sndpan = nxdis# / 150
			playWaveSound()
			
		end if
		
		Return mpp
	;End If
End Function			
		
Function UpdateMpa(mpp.Mpa)
	If mpp\life<0 Or Rnd(-2,20)<0 Or EntityInView(mpp\mpapivot,camera)=False
		FreeEntity mpp\mpapivot
		Delete mpp
		Return
	Else If mpp\life<799
		mpp\mwidth#=mpp\mwidth# +1.4
	End If
	tmp#=mpp\mheight#
	mpp\mheight#=mpp\mheight# * .99*mpp\life/800
	If mpp\mheight#<0.2 mpp\mheight#=.2
	ScaleEntity mpp\mpapivot,mpp\mwidth#*mpp\life/800,mpp\mheight#,mpp\mdepth#*mpp\life/800

	tmp#=getalt#(mpp\mpapivot,-.1*mpp\mheight#)-EntityY#(mpp\mpapivot,True)+Rnd#(-0.2,0.2)
	TranslateEntity mpp\mpapivot,2* (mpp\life/800)^2,tmp#,-.1*(tmp#-mpp\mheight#),True
	mpp\life=mpp\life-Rnd(0,8)
	EntityAlpha mpp\mpapivot,0.9* mpp\life/800 +0.05
	
	
	
 
		pan =(  EntityZ#(mpp\mpapivot,True) - EntityZ#(camera,True) ) / 100
		if pan < 1 or pan > -1
		
			if mpp\life/800 <100 
				;StopChannel chnWave 
		
			else 
				SoundPan sndWave, pan*0.5
				
				SoundVolume sndWave, mpp\mheight# / 50
				
				
			end if
		
		end if
		
		
		
		
	
	 
	
	Return 
End Function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;SETUP;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Function updateSky()

	PositionEntity sky, EntityX(car,True)-500,900,EntityZ(car,True)-500

End Function






Function loadSkySphereBACK(file$)
	; Create sphere
	sphere=CreateSphere()
	
	; Scale sphere
	ScaleEntity sphere,100,100,100
	
	; Texture sphere with sky texture
	sky_tex=LoadTexture(file$+"_UP.jpg")
	EntityTexture sphere,sky_tex
	EntityAlpha sphere,1
	FlipMesh sphere
	Return sphere

End Function

Function loadSkySphere()

	skyplane=LoadSprite("_Medias/Sky/LargeTerrainSky.jpg",10)
	;skyplane=CreatePlane()
	ScaleSprite skyplane,8000,5000
	skiteObjxtr=LoadTexture("_Medias/Sky/LargeTerrainSky.jpg",2)
	ScaleTexture skiteObjxtr,1,1
	EntityTexture skyplane,skiteObjxtr
	;PositionTexture skiteObjxtr,-1000,1000
	HandleSprite skyplane,0,-1
	SpriteViewMode skyplane,3
	;TurnEntity skyplane,270,0,0
	PositionEntity skyplane,0,-50,9500
	EntityAlpha skyplane,1
	
	Return skyplane

End Function


Function LoadSkyBox( file$ )
	m=CreateMesh()
	;front face
	b=LoadBrush( file$+"_FR.jpg",1 )
	s=CreateSurface( m,b )
	AddVertex s,-1,+1,-1,0,0:AddVertex s,+1,+1,-1,1,0
	AddVertex s,+1,-1,-1,1,1:AddVertex s,-1,-1,-1,0,1
	AddTriangle s,0,1,2:AddTriangle s,0,2,3
	FreeBrush b
	;right face
	b=LoadBrush( file$+"_LF.jpg",1 )
	s=CreateSurface( m,b )
	AddVertex s,+1,+1,-1,0,0:AddVertex s,+1,+1,+1,1,0
	AddVertex s,+1,-1,+1,1,1:AddVertex s,+1,-1,-1,0,1
	AddTriangle s,0,1,2:AddTriangle s,0,2,3
	FreeBrush b
	;back face
	b=LoadBrush( file$+"_BK.jpg",1 )
	s=CreateSurface( m,b )
	AddVertex s,+1,+1,+1,0,0:AddVertex s,-1,+1,+1,1,0
	AddVertex s,-1,-1,+1,1,1:AddVertex s,+1,-1,+1,0,1
	AddTriangle s,0,1,2:AddTriangle s,0,2,3
	FreeBrush b
	;left face
	b=LoadBrush( file$+"_RT.jpg",1 )
	s=CreateSurface( m,b )
	AddVertex s,-1,+1,+1,0,0:AddVertex s,-1,+1,-1,1,0
	AddVertex s,-1,-1,-1,1,1:AddVertex s,-1,-1,+1,0,1
	AddTriangle s,0,1,2:AddTriangle s,0,2,3
	FreeBrush b
	;top face
	b=LoadBrush( file$+"_UP.jpg",1 )
	s=CreateSurface( m,b )
	AddVertex s,-1,+1,+1,0,1:AddVertex s,+1,+1,+1,0,0
	AddVertex s,+1,+1,-1,1,0:AddVertex s,-1,+1,-1,1,1
	AddTriangle s,0,1,2:AddTriangle s,0,2,3
	FreeBrush b
	;bottom face	
	b=LoadBrush( file$+"_DN.jpg",1 )
	s=CreateSurface( m,b )
	AddVertex s,-1,-1,-1,1,0:AddVertex s,+1,-1,-1,1,1
	AddVertex s,+1,-1,+1,0,1:AddVertex s,-1,-1,+1,0,0
	AddTriangle s,0,1,2:AddTriangle s,0,2,3
	FreeBrush b
	ScaleMesh m,16000,16000,16000
	FlipMesh m
	EntityFX m,1
	Return m
End Function


Function Setup()

;	skyplane=LoadSprite("res/front.jpg",1);_Medias/Sky/sky2.bmp",10)
	;skyplane=CreatePlane()
;	ScaleSprite skyplane,600,600
	;skiteObjxtr=LoadTexture("_Medias/Sky/sky2.bmp",1)
	;ScaleTexture skiteObjxtr,1,1
	;EntityTexture skyplane,skiteObjxtr
	;PositionTexture skiteObjxtr,-1000,1000
;	HandleSprite skyplane,0,-1
;	SpriteViewMode skyplane,3
	;TurnEntity skyplane,270,0,0
;;	PositionEntity skyplane,0,-300,150
;	EntityAlpha skyplane,1
	
;	skyplaneroof1=LoadSprite("_Medias/Sky/sky2.bmp",1)
;	ScaleSprite skyplaneroof1,25000,6000
;	HandleSprite skyplaneroof1,0,-1
;	SpriteViewMode skyplaneroof1,3
;	PositionEntity skyplaneroof1,0,-1000,-20000
;	EntityAlpha skyplaneroof1,1
	
;	skyplaneroof2=LoadSprite("_Medias/Sky/sky2.bmp",1)
;	ScaleSprite skyplaneroof2,25000,6000
;	HandleSprite skyplaneroof2,0,-1
;	SpriteViewMode skyplaneroof2,3
;	PositionEntity skyplaneroof2,25000,-1000,0
;	EntityAlpha skyplaneroof2,1
	
;	skyplaneroof3=LoadSprite(";_Medias/Sky/back.jpg",1);_Medias/Sky/sky2.bmp",10)
;	ScaleSprite skyplaneroof3,35000,6000
;	HandleSprite skyplaneroof3,0,-1
;	SpriteViewMode skyplaneroof3,3
;	PositionEntity skyplaneroof3,-20000,-2500,0
;	EntityAlpha skyplaneroof3,1
	
	
	
	
	
	
	rocher_sprite=LoadSprite( "_Medias/sprites/rocher.bmp",2 )
	ScaleSprite rocher_sprite,1750,150
	HandleSprite rocher_sprite,0,-1.5
	PositionEntity rocher_sprite,2000,-100,12000
	SpriteViewMode rocher_sprite,3
	EntityAutoFade rocher_sprite,13000,15000
	EntityOrder rocher_sprite, 25

	
	arbre_sprite=LoadSprite( "_medias/Sprites/Plante.png",2);_Medias/sprites/petitarbre2.bmp",5 )
	ScaleSprite arbre_sprite,6,3
	HandleSprite arbre_sprite,0,-.5
	;PositionEntity arbre_sprite,0,-10,0
	SpriteViewMode arbre_sprite,3
	EntityAutoFade arbre_sprite,6000,6500
	
	tree_sprite=LoadSprite( "_Medias/sprites/cocotier4.png",2 )
	HandleSprite tree_sprite,0,-1
	ScaleSprite tree_sprite,2,5
	PositionEntity tree_sprite,0,-10,0
	SpriteViewMode tree_sprite,3
	EntityAutoFade tree_sprite,6000,7500
	tree_sprite2=LoadSprite( "_Medias/sprites/cocotier5.png",2 )
	HandleSprite tree_sprite2,0,-1
	ScaleSprite tree_sprite2,2,4
	PositionEntity tree_sprite2,0,-10,0
	SpriteViewMode tree_sprite2,3
	EntityAutoFade tree_sprite2,6500,7500
	
	;coco_sprite=LoadSprite( "_Medias/sprites/coco.bmp",5 )
	;HandleSprite coco_sprite,0,-0.2
	;ScaleSprite coco_sprite,0.5,0.5
	;PositionEntity coco_sprite,0,-10,0
	;SpriteViewMode coco_sprite,3
	;EntityAutoFade coco_sprite,500,1000

	groupie_sprite=LoadSprite( "_Medias/sprites/groupy.png",2 )
	HandleSprite groupie_sprite,0,0
	ScaleSprite groupie_sprite,1.2,1.2
	PositionEntity groupie_sprite,0,-10,0
	SpriteViewMode groupie_sprite,3
	EntityAutoFade groupie_sprite,2500,3000
	EntityShininess groupie_sprite,1
	EntityFX groupie_sprite,3
	groupie2_sprite=LoadSprite( "_Medias/sprites/groupy2.png",2 )
	HandleSprite groupie2_sprite,0,0
	ScaleSprite groupie2_sprite,1.2,1.2
	PositionEntity groupie2_sprite,0,-10,0
	SpriteViewMode groupie2_sprite,3
	EntityAutoFade groupie2_sprite,2500,3000
	EntityShininess groupie2_sprite,1
	EntityFX groupie2_sprite,3
	
	nuage_sprite=LoadSprite("_Medias/sprites/nuagesronds.bmp",5)
	ScaleSprite nuage_sprite,1,1
	PositionEntity nuage_sprite,0,800,2000
	HandleSprite nuage_sprite,0,0
	SpriteViewMode nuage_sprite,1
	EntityAutoFade nuage_sprite,1000,10000
	PositionEntity nuage_sprite,0,-100,0
	
	particule_sprite=LoadSprite( "_Medias/sprites/w_particule_ronde.bmp",12 )
	ScaleSprite particule_sprite,1,1
	HandleSprite particule_sprite,0,0
	PositionEntity particule_sprite,0,0,0
	SpriteViewMode particule_sprite,1
	EntityAutoFade particule_sprite,2500,3000
	
;	wparticule_sprite=LoadSprite( "sprites\w_particule_ronde.bmp",5)
;	ScaleSprite wparticule_sprite,1,1
;	HandleSprite wparticule_sprite,0,0
;	PositionEntity wparticule_sprite,0,0,0
;	SpriteViewMode wparticule_sprite,1
;	EntityAutoFade wparticule_sprite,2500,3000
	 
;	particulep_sprite=LoadSprite( "sprites\particule_ronde.bmp",5)
;	HandleSprite particulep_sprite,0,0
;	ScaleSprite particulep_sprite,1,1
;	PositionEntity particulep_sprite,0,0,0
;	SpriteViewMode particulep_sprite,2
;	EntityAutoFade particulep_sprite,2500,3000
;	TurnEntity particulep_sprite,90,0,0
	;EntityParent particulep_sprite,particule_mousse
	
	
	shark_sprite=LoadSprite( "_Medias/sprites/sharko.bmp",5 )
	ScaleSprite shark_sprite,1,1
	HandleSprite shark_sprite,-0.7,-1
	PositionEntity shark_sprite,0,0,0
	SpriteViewMode shark_sprite,2
	EntityAutoFade shark_sprite,1500,2000
	shark_sprite2=LoadSprite( "_Medias/sprites/sharko2.bmp",5 )
	ScaleSprite shark_sprite2,1,1
	HandleSprite shark_sprite2,0.7,-1
	PositionEntity shark_sprite2,0,0,0
	SpriteViewMode shark_sprite2,2
	EntityAutoFade shark_sprite2,1500,2000
	
	moussmod=LoadMesh("_Medias/Mousse/mesh_mousse2.3ds")
	FitMesh moussmod,-0.5,-0.5,-0.5,1,1.5,1.5	
	EntityTexture moussmod,mousstextr
	EntityAlpha moussmod,1
	EntityShininess moussmod,1
	EntityColor moussmod,250,250,255
	EntityType moussmod,TYPE_MOUSS
	
	
	
	
	

	
End Function
	
Function getalt#(name$,offset#)	
	wreste# = (EntityX#(name$,True) - transla#) Mod pasxl#
	zreste# = EntityZ#(name$,True) Mod paslarge#
	geth(EntityX#(name$,True)- wreste#+((p7x#+p0x#)*.5),EntityZ#(name$,True)-zreste#)
	wwavecoef#=hlresult#
	tmp#=mhlresult#
	geth(EntityX#(name$,True) - wreste#+((p7x#+p0x#)*.5),EntityZ#(name$,True)-zreste#+paslarge#)
	wwavecoef2#=hlresult#
	mhlresult#=tmp# + zreste#*(mhlresult#-tmp#)/paslarge#
	kkcoef# = wwavecoef# + zreste#*(wwavecoef2# - wwavecoef#)/paslarge#			
	clvl# = getcourbey#(wreste#) * kkcoef# + offset#	
	relief# = getlvl#(name$)
	testrelief=0
	kspeed# = 0.97
	If relief#>clvl# testrelief=1:kspeed# = 0.8:Return relief#
	;If name$=car And mhlresult#>0 And wreste#>p2x# And wreste#<p4x# And EntityY#(car,True)<clvl#+1
		;TurnEntity car,Rnd#(-110,110),Rnd#(-110,110),Rnd#(-110,110)
		;scorevie=scorevie-5
	;clvl#=clvl#+Rnd#(-1.5,1.5)
	;End If
	Return 	clvl#
End Function		



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  
;;;;;;;;;;;;;;;;;;;;;;;;MOUSSES
 
;Function CreateMv.Mv(tvmx#,tvmy#,tvmz#)
;	vv.Mv= New Mv
;	vv\vsmx# = tvmx# 
;	vv\vsmy# = tvmy# 
;	vv\vsmz# = tvmz#
;	vv\vstime_out=250
;	vv\vssprite=CreatePivot()
;	vv\vpivot= CopyEntity( moussmod,vv\vssprite )
;	EntityAlpha vv\vssprite,1	
;	FitMesh vv\vssprite,0,0,0,1,1,1
;	PositionEntity vv\vssprite,vv\vsmx#,vv\vsmy#,vv\vsmz#,True
;	Return vv
;End Function

;Function UpdateMv( vv.Mv )
;	vv\vstime_out = vv\vstime_out-1
;	vvwreste# = (EntityX(vv\vssprite,True)+(0.5*MeshWidth(vv\vssprite)) - transla#) Mod pasxl#
;	vvwwavecoef# = geth(EntityX(vv\vssprite,True),(EntityZ(vv\vssprite,True)+0.5*MeshDepth(vv\vssprite)))
;	vvwheellvl# = getcourbey#(vvwreste#) * vvwwavecoef#
;	PositionEntity vv\vssprite,EntityX(vv\vssprite,True),vvwheellvl#+1,EntityZ(vv\vssprite,True)
;	If vv\vstime_out >200
;		RotateEntity vv\vssprite,0,0,-10
;		vvtmp#=(250-vv\vstime_out)	
;		TranslateEntity vv\vssprite,-1,-0,-1.5,True
;		ScaleEntity vv\vssprite,1*vvtmp#,0.3*vvtmp#*vvwwavecoef#,3*vvtmp#
;		Return
;	Else If vv\vstime_out =200
;		PaintMesh vv\vssprite,brush
;		RotateEntity vv\vssprite,0,0,-5
;		Return
;	Else If vv\vstime_out <50
;		;delete MOUSS
;		FreeEntity vv\vssprite
;		Delete vv
;		Return	
;	Else If vv\vstime_out <200 
;		RotateEntity vv\vssprite,0,0,0	
;		TranslateEntity vv\vssprite,0,0,1,True
;		ScaleEntity vv\vssprite,4+(0.5*(vv\vstime_out-50)),(10*(vv\vstime_out-50)/150)+1,0.5*(vv\vstime_out-50)
;		Return
;	EndIf

;End Function	




