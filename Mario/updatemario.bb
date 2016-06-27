;____________________________________________________update kiteObj
	

	;calculate car velocities	
	
	cx#=EntityX( car ):x_vel#=cx#-prev_x#:prev_x#=cx#
	cy#=EntityY( car ):y_vel#=cy#-prev_y#:prev_y#=cy#
	cz#=EntityZ( car ):z_vel#=cz#-prev_z#:prev_z#=cz#
	
	
	windx#= WWindx# - x_vel#
	windy#= WWindy# - y_vel#
	windz#=	WWindz# - z_vel#
	wind# = Sqr((windx# *windx#) + (windy#*windy#) + (windz#*windz#))

	repwindx#=EntityX#(car,True)+(windx# * distkiteObj / wind#)
	repwindy#=EntityY#(car,True)+(windy# * distkiteObj / wind#)
	repwindz#=EntityZ#(car,True)+(windz# * distkiteObj / wind#)
	PositionEntity repwind,repwindx#,repwindy#,repwindz#
	PositionEntity pkiteObjc,EntityX(car,True),EntityY(car,True),EntityZ(car,True)

	If KeyDown(73) TurnEntity pkiteObjc,0,0,-12 ;kiteObjr

	If KeyDown(71) TurnEntity pkiteObjc,0,0,12;kiteObjr
	
	n_dis#=EntityDistance#(kiteObj,repwind):d_dis# = n_dis# - v_dis# 
	
	vitesserota# = (Wind#*((Sqr(2)*(distkiteObj+3)) - EntityDistance#(kiteObj,repwind)))/80 ;windwheel
	
	
	If vitesserota#>2.8 vitesserota#=2.8
	If vitesserota#<1.5 vitesserota#=1.5
	corp# = vitesserota# * (Sin(EntityRoll(pkiteObjc,True)-90))

	
	cory# = vitesserota# * (Cos(EntityRoll(pkiteObjc,True)-90)) ;Sin(EntityRoll(pkiteObjc))*Cos(EntityRoll(pkiteObjc))*Cos(EntityPitch(pkiteObjc));*Sgn(d_dis#)
	corz#= 0;(90-EntityRoll#(pkiteObjc,True))/360 ;(-(EntityYaw(pkiteObjc,True)) - EntityRoll#(pkiteObjc,True) )*0.02
	
	
	;TurnEntity pkiteObjc,vitesserota#/1,0,0,True ;-cor#/1,corZ#/1
	
	ppwind = CreatePivot()
	PositionEntity ppwind,EntityX(car,True),EntityY(car,True),EntityZ(car,True)
	PointEntity ppwind,repwind
	
	newpitch# = EntityPitch#(pkiteObjc,True)+corp#
	newyaw# = (EntityYaw#(pkiteObjc,True)+cory#)
	newroll# = EntityRoll#(pkiteObjc,True)+corz# ;((90-EntityRoll#(pkiteObjc,True))/360)
		
	If newyaw# < EntityYaw#(ppwind,True)-57 newyaw# = EntityYaw#(ppwind,True)-57:newroll# = newroll# + (Sgn(newyaw)*90 - newroll#)*0.05
	If newyaw# > EntityYaw#(ppwind,True)+ 57 newyaw# = EntityYaw#(ppwind,True)+57:newroll# = newroll# + (Sgn(newyaw)*90 - newroll#)*0.05
	If newpitch# < EntityPitch#(ppwind,True)-70 newpitch# = EntityPitch#(ppwind,True)-70:newroll# = newroll# + (Sgn(newyaw)*90 - newroll#)*0.05
	If newpitch# > EntityPitch#(ppwind,True) +70 newpitch# = EntityPitch#(ppwind,True)+70:newroll# = newroll# + (Sgn(newyaw)*90 - newroll#)*0.05
		
		
	RotateEntity pkiteObjc,newpitch#,newyaw#,newroll#
	
	v_dis# = EntityDistance#(kiteObj,repwind)
	
	
	;________________________________________________________update car

	;RotateEntity ecum,Sgn(speed)*(-Sgn(speed)*EntityPitch(car,True)+90),EntityYaw(car,True),EntityRoll(car,True)
	;;;;;;;;;If KeyDown(57) RotateEntity car,0,newyaw#-90,0 ;newroll#
	
	;input move car
	If KeyDown(28) RotateEntity car,0,EntityYaw# (pkiteObjc,True),0
	If KeyDown(78) MoveEntity car,0,1,3:x_vel#=0:prev_x#=EntityX#( car ):y_vel#=0:prev_y#=EntityY#( car ):z_vel#=0:prev_z#=EntityZ#( car )

	planing# = getplaning#(speed#)
	clvl# = getalt#(car,planing#)
	;recifpa=0
	;If testrelief=1 Then 
	;	recifpa=1
	;End If
	
	;kiteObj->car
	ctemp# =  wind# * Cos(EntityPitch(pkiteObjc,True))/13
	cvol# = (1+Rnd#(-0.1,0.6))* -GRAVITY# * (Sin(EntityPitch(pkiteObjc,True)-EntityPitch#(ppwind,True)))^3*(Cos(EntityYaw#(pkiteObjc,True)-EntityYaw#(ppwind,True)))^2
	
		

	speed#=speed#*1
	If EntityY#(car,True) < clvl#+0.7   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;sur sol
	speed#=speed#*kspeed#
	;comptyaw=EntityYaw(car,True)
	If KeyDown(77) And (EntityRoll# (car,True) < 90)  TurnEntity car, -3/(speed),-3,-3*Sgn(speed):createPa(testrelief,0,1,3,3,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.8) :comptyaw=comptyaw-3
	If KeyDown(75) And (EntityRoll# (car,True) > -90)  TurnEntity car,-3/(speed),3,3*Sgn(speed):createPa(testrelief,0,1,3,3,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.8) :comptyaw=comptyaw+3
	If KeyDown(81) And (EntityRoll# (car,True) < 100)  TurnEntity car, -7/(speed),-7,-7*Sgn(speed):createPa(testrelief,1,1,3,4,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.8) :comptyaw=comptyaw-7
	If KeyDown(79) And (EntityRoll# (car,True) > -100)  TurnEntity car,-7/(speed),7,7*Sgn(speed):createPa(testrelief,1,1,3,4,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.8) :comptyaw=comptyaw+7

	TurnEntity car,(Sgn(speed#)*(-5) - EntityPitch(car,True))*1,0,(Sin(EntityYaw(car,True)-EntityYaw(pkiteObjc,True))*(EntityPitch(pkiteObjc,True)+90)/2 -EntityRoll#(car,True))*.2
	
		If ecumx# =1 ;;---------------------------------------reception de saut
			vcomptyaw#=EntityYaw#(pkiteObjc,True)
			score=((yawtour*90)+2*(pitchtour*90)+2*(rolltour*90)+scorehaut*scorelong)*scorebonus*scoresens
			tscore=tscore+score
			ecumx# = 0
			createPa(testrelief,1,3,5,7,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.4)
		;	tcomptyaw#=EntityYaw#(car,True)+90-EntityYaw#(pkiteObjc,True)
		;	vcomptyaw#=tcomptyaw#
		;	ttouryaw#=tcomptyaw#
		End If	
			
		PositionEntity car,EntityX#(car,True), clvl# + shattack#, EntityZ#(car,True)
		
		prev_y# = clvl#
		;resposition wheels
		i=1
		For z#=0.5 To -0.5 Step -1
			For x#=-0.5 To 0.5 Step 1
				planing# = getplaning#(speed#)
				wheellvl# = getalt#(wheels[i],planing#)
				PositionEntity wheels[i],x#,(wheellvl#)-EntityY#(car,True), z# 
				i=i+1
			Next
		Next	
		;align car to wheels
		zx#=(EntityX( wheels[2],True )+EntityX( wheels[4],True ))/2
		zx=zx-(EntityX( wheels[1],True )+EntityX( wheels[3],True ))/2
		zy#=(EntityY( wheels[2],True )+EntityY( wheels[4],True ))/2
		zy=zy-(EntityY( wheels[1],True )+EntityY( wheels[3],True ))/2
		zz#=(EntityZ( wheels[2],True )+EntityZ( wheels[4],True ))/2
		zz=zz-(EntityZ( wheels[1],True )+EntityZ( wheels[3],True ))/2
		AlignToVector car,zx,zy,zz,1
		zx#=(EntityX( wheels[1],True )+EntityX( wheels[2],True ))/2
		zx=zx-(EntityX( wheels[3],True )+EntityX( wheels[4],True ))/2
		zy#=(EntityY( wheels[1],True )+EntityY( wheels[2],True ))/2
		zy=zy-(EntityY( wheels[3],True )+EntityY( wheels[4],True ))/2
		zz#=(EntityZ( wheels[1],True )+EntityZ( wheels[2],True ))/2
		zz=zz-(EntityZ( wheels[3],True )+EntityZ( wheels[4],True ))/2
		AlignToVector car,zx,zy,zz,3

		;actions
	
		cspeed# =  ctemp# * Cos(EntityYaw#(car,True)-EntityYaw#(pkiteObjc,True))
		cspeed# = cspeed# - Sin(EntityPitch#(car,True))*GRAVITY#*0.5
		cderapage# = ctemp# * Sin(EntityYaw#(car,True)-EntityYaw#(pkiteObjc,True))
		If Abs(speed#)>2 cderapage# = 2*cderapage#/Abs(speed#)

		speed# = 0.95*(speed# + cspeed# );*kspeed#
		If speed#>speedmax# speed#=speedmax#
		If speed#<-speedmax# speed#=-speedmax#
		
		
		If testrelief =0	;
			createPa(testrelief,0,0,1.5,2,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.8)
			MoveEntity car,cderapage#/3,0,speed#/3
			createPa(testrelief,0,0,1.5,2,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.8)
			MoveEntity car,cderapage#/3,0,speed#/3
			createPa(testrelief,0,0,1.5,2,EntityX#(car,True)-x_vel#*0.3,EntityY#(car,True)+0.05,EntityZ#(car,True)-z_vel#*.8)
			MoveEntity car,cderapage#/3,0,speed#/3
		Else ;
			MoveEntity car,cderapage#,0,speed#;
		EndIf	;
		comptasaut = comptasaut - 1
		If KeyDown(76) And comptasaut<0 Then TranslateEntity car,0,1-Sin#(EntityPitch(pkiteObjc,True)),0:comptasaut =5



	Else If EntityY#(car,True)>=(clvl#+0.7) ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;en l'air
	
	If KeyDown(77) And (EntityRoll# (car,True) < 90)  TurnEntity car, -10/(speed),-10,-10*Sgn(speed) 
	If KeyDown(75) And (EntityRoll# (car,True) > -90)  TurnEntity car,-10/(speed),10,10*Sgn(speed) 
	If KeyDown(81) And (EntityRoll# (car,True) < 100)  TurnEntity car, -20/(speed),-20,-20*Sgn(speed) 
	If KeyDown(79) And (EntityRoll# (car,True) > -100)  TurnEntity car,-20/(speed),20,20*Sgn(speed) 

	TurnEntity car,(Sgn(speed#)*(-5) - EntityPitch#(car,True))*.3,0,((Sin(EntityYaw#(car,True)-EntityYaw#(pkiteObjc,True))*(EntityPitch#(pkiteObjc,True)+90)/2 -EntityRoll#(car,True))*.2)

		If ecumx#<1 
			pitchtour=0:yawtour=0:rolltour=0
			scoresens=1:scorebonus=1:ecumx=1:score=0
			scorepitch0=EntityPitch(car,True):scoreyaw0=EntityYaw(car,True):scoreroll0=EntityRoll(car,True)
			scorelong=0:scorehaut=EntityY(car,True)
			scorelongx=EntityX(car,True):scorelongz=EntityZ(car,True)
		End If
		
		
		turncar1#=0:turncar2=0:turncar3#=0
		
		If KeyDown(77) And (EntityRoll# (car,True) < 140) ;d
			turncar1#= -1/(speed):turncar2=-6:turncar3#=-1*Sgn(speed)
			If sens=2 scoresens=scoresens+1
			sens=1	
		Else If KeyDown(75) And (EntityRoll# (car,True) > -140) ;g
			turncar1#=-1/(speed):turncar2=6:turncar3#=1*Sgn(speed) 
			If sens=1 scoresens=scoresens+1
			sens=2	
		Else If KeyDown(81) And (EntityRoll# (car,True) < 140) ;d
			turncar1#= -2/(speed):turncar2=-8:turncar3#=-2*Sgn(speed) 
			If sens=2 scoresens=scoresens+1
			sens=1	
		Else If KeyDown(79) And (EntityRoll# (car,True) > -140) ;g
			turncar1#=-2/(speed):turncar2=8:turncar3#=2*Sgn(speed)
			If sens=1 scoresens=scoresens+1
			sens=2		
		End If
		
		TurnEntity car,turncar1#,turncar2,turncar3#	
							
		;resposition wheels
		i=1
		For z#=0.5 To -0.5 Step -1
			For x#=-0.5 To 0.5 Step 1
				PositionEntity wheels[i],x#,0,z#
				i=i+1
			Next
		Next
		
		;actions
		cspeed# = - ctemp# * Sin(EntityYaw#(pkiteObjc,True)) 
		cderapage# = ctemp# * Cos(EntityYaw#(pkiteObjc,True))
		cvol# = - cvol# +GRAVITY#
		
		x_vel# = x_vel# + cspeed#
		y_vel# = y_vel# + cvol# 
		z_vel# = z_vel# + cderapage#
		
		If x_vel#>speedmax# x_vel#=speedmax#
		If y_vel#>speedmax# y_vel#=speedmax#
		If z_vel#>speedmax# z_vel#=speedmax#
		If x_vel#<-speedmax# x_vel#=-speedmax#
		If y_vel#<-speedmax# y_vel#=-speedmax#
		If z_vel#<-speedmax# z_vel#=-speedmax#
		
		TranslateEntity car,x_vel#,y_vel#,z_vel#

		speed# = x_vel#*Cos(-90-EntityYaw#(car,True)) + z_vel#*Cos(EntityYaw#(car,True))
		
		If EntityPitch#(car,True)-scorepitch0 <-85 Or  EntityPitch#(car,True)-scorepitch0>85
			pitchtour=pitchtour+1
			scorepitch0=EntityPitch#(car,True)
		End If

		If EntityYaw#(car,True)-scoreYaw0 <-85 Or EntityYaw#(car,True)-scoreYaw0>85
			Yawtour=Yawtour+1
			scoreYaw0=EntityYaw#(car,True)
		End If
		
		If EntityRoll#(car,True)-scoreroll0 <-85 Or EntityRoll(car,True)-scoreroll0>85
			rolltour=rolltour+1
			scoreroll0=EntityRoll#(car,True)
		End If
	
		
		If (0.3*EntityY#(car,True)) > scorehaut scorehaut =0.3*EntityY#(car,True)
		scorelong =0.3*Sqr( (EntityX#(car,True)-scorelongx)^2 + (EntityZ#(car,True)-scorelongz)^2 )
		EndIf
		
		;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		
		


		;TurnEntity falsecar 180, 180, 180
		newFalseHauteur#=clvl#-0.3
		
		If(EntityY#(car,True)>=(clvl#+0.7))
			newFalseHauteur#=-EntityY#(car,True)
		End If 
		
		;tmp=geth#(EntityX#(car,True),EntityZ#(car,True))
		
		
		PositionEntity falsecar ,EntityX#(car,True), newFalseHauteur#, EntityZ#(car,True)
		RotateEntity falsecar , 180-EntityPitch#(car),180+ EntityYaw#(car),-EntityRoll#(car)
		
	;	PositionEntity falsemario ,EntityX#(playr,True), newFalseHauteur#, EntityZ#(playr,True)
	;	RotateEntity falsemario , 180-EntityPitch#(playr),180+ EntityYaw#(playr),-EntityRoll#(playr)

		PositionEntity falsepkiteObjc,EntityX#(pkiteObjc,True), newFalseHauteur#, EntityZ#(pkiteObjc,True)
		RotateEntity falsepkiteObjc, -EntityPitch#(pkiteObjc,True), EntityYaw#(pkiteObjc,True), -EntityRoll#(pkiteObjc,True)
		
		If KeyDown(11)switchFalse=switchFalse+1
		
		If (switchFalse>5)switchFalse=0
	
		If(getlvl#(falsekiteObj)>0.5) Or (switchFalse>2)
			HideEntity falsecar 
			HideEntity falsekiteObj
			
		Else 
			ShowEntity falsecar 
			ShowEntity falsekiteObj
	
		End If
		
		
		
		
		
	
	;;;;;;;;;;;;;;;;;;;;;;;;;;; angleBarre angleBarre
angleBarre=EntityYaw(pkiteObjc,True)-EntityYaw(car,True)

anglebarre=(angleBarre* -scoresens) + 90

If(angleBarre>180)angleBarre=angleBarre-360
If(angleBarre<-180)angleBarre=angleBarre+360



If(angleBarre<=45) And (angleBarre>=-45)
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	HideEntity barreLeftC
	HideEntity barreRightC
	
	ShowEntity barreNormal
	
	
	
Else If (angleBarre<-55) And (angleBarre>=-90)
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	HideEntity barreLeftC
	HideEntity barreRightC
	
	ShowEntity barreRightA
	
Else If (angleBarre<-90) And (angleBarre>=-135)
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	HideEntity barreLeftC
	HideEntity barreRightC
	
	ShowEntity barreRightC
	
Else If (angleBarre<-135) And (angleBarre>=-180)
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	HideEntity barreLeftC
	HideEntity barreRightC
	
	ShowEntity barreRight 
	
	
	
Else If (angleBarre<180) And (angleBarre>=135)
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	HideEntity barreLeftC
	HideEntity barreRightC
	
	ShowEntity barreLeft 
	
Else If (angleBarre<135) And (angleBarre>=90)
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	HideEntity barreLeftC
	HideEntity barreRightC
	
	ShowEntity barreLeftC
	
Else If (angleBarre<90) And (angleBarre>=45)
	HideEntity barreNormal
	HideEntity barreLeftA
	HideEntity barreLeft
	HideEntity barreRightA
	HideEntity barreRight
	HideEntity barreBack
	HideEntity barreLeftC
	HideEntity barreRightC
	
	ShowEntity barreLeftA
	
	
	
End If 



 	;PositionEntity cylinder,EntityX(car,True)+1,EntityY(car,True)+2.6,EntityZ(car,True)+.12
	;TurnEntity cylinder 0,-90,0


	
		
	