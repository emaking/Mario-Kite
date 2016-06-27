	If KeyDown(203) TurnEntity l,5,0,0 
	If KeyDown(205) TurnEntity l,0,5,0 
	If KeyDown(200) TurnEntity l,0,0,5 	
	
	
	


	If KeyDown(38) ;L
	
	
		;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; POSITION
		If KeyDown(45) ;X
			If KeyDown(78);+
				PositionEntity l,EntityX(l, True)+10,EntityY(l,True),EntityZ(l,True) 

			Else If KeyDown(74);-
				PositionEntity l,EntityX(l, True)-10,EntityY(l,True),EntityZ(l,True) 
			
			End If
	
	
		Else If KeyDown(21) ;Y
			If KeyDown(78);+
				PositionEntity l,EntityX(l, True),EntityY(l,True)+10,EntityZ(l,True) 

			Else If KeyDown(74);-
				PositionEntity l,EntityX(l, True),EntityY(l,True)-10,EntityZ(l,True) 
			
			End If

	
		Else If KeyDown(44) ;Z
			If KeyDown(78);+
				PositionEntity l,EntityX(l, True),EntityY(l,True),EntityZ(l,True)+10

			Else If KeyDown(74);-
				PositionEntity l,EntityX(l, True),EntityY(l,True),EntityZ(l,True)-10
			
			End If
		
		
		
		
		;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; INIT
		Else If KeyDown(23) ;I
			PositionEntity l,EntityX(car, True),EntityY(car,True)+20,EntityZ(car,True)
			
			
		;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;	COLOR
			
		Else If KeyDown(19) ;R
			If KeyDown(78);+
				lr=lr+2
				LightColor l,lr,lv,lb

			Else If KeyDown(74);-
				lr=lr-2
				LightColor l,lr,lv,lb

			
			End If
		
		Else If KeyDown(47) ;V
			If KeyDown(78);+
				lv=lv+2
				LightColor l,lr,lv,lb
 

			Else If KeyDown(74);-
				lv=lv-2
				LightColor l,lr,lv,lb
 
			
			End If

		Else If KeyDown(48) ;B
			If KeyDown(78);+
				lb=lb+2
				LightColor l,lr,lv,lb


			Else If KeyDown(74);-
				lb=lb-2
				LightColor l,lr,lv,lb

			
			End If


	
		End If
	
	
	End If
	
	
strmess$="LIGHT POSITION X="+EntityX(l,True)+ ", Y="+ EntityY(l,True)+ ", Z="+EntityZ(l,True) + " | LIGHT COLOR = Rouge="+lr+", Vert="+lv+", Blue="+lb+" | "

;SetFont fntArialB
;Text largeurE/2,(HauteurE*3/4)+(HauteurE/4)*1.5/5, strmess$,True,False








