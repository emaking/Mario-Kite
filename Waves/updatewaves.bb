;;;;;;;;;;;;;;;;;
	transla# = transla# - 2
	If (transla# - EntityX(car,True)) > - 4*pasxl# 
		transla#= transla# - (pasxl#)
	Else If (transla# - EntityX(car,True)) < -5*pasxl# 
		transla#= transla# + (pasxl#)
	End If
													
	;creation  de la vague : mesh
	mesh=CreateMesh()
	surf=CreateSurface( mesh )
	prunch = 0

	For large# = 0 To resollarge#
		
		If large# = resollarge#
			prunch= 5000	
		Else If large#=0 
			prunch = -1000 
		Else
			prunch = 0
		End If
		
		wz#= large#*paslarge#  
		v#=Float(large)*paslarge#
		
		For xl#=0 To resolxl# + 2 	
			If xl# = 0
				u#=EntityX(car,True)-5000 
				;;;;
				wx#= EntityX(car,True)-5000 
				wy#= 0 
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch			
			Else If xl# < resolxl#+2
				geth#(((xl#-1)*pasxl# +transla# +p0x+(p7x#-p0x#)*.5),wz#)		
				;point1
				u#= Float(xl#-1)*pasxl# +transla# 
				;;;;
				wx#= (xl#-1) * pasxl# + transla# +pax#
				wy#= getcourbey#(pax#) *hlresult# 
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch				
				;point2
				u# = u# + (p0x#-pax#) 
				;;;;
				wx#= wx# + (p0x#-pax#) 				
				wy#= getcourbey#(p0x#) *hlresult# 
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch							
				;point3
				u# = u# + (p1x#-p0x#) 			
				;;;;
				wx#= wx# + (p1x#-p0x#) 	
				wy#= getcourbey#(p1x#) *hlresult# 
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch
				;point3b
				u# = u# + (p2x#-p1x#) 
				;;;;
				wx#= wx# + (p2x#-p1x#) 
				wy#= getcourbey#(p2x#) *hlresult# 
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch				
				;point4
				u# = u# + (p3x#-p2x#) *1.5
				;;;;
				wx#= wx# + (p3x#-p2x#) 
				wy#= getcourbey#(p3x#) *hlresult#
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch
				;point5
				u# = u# + (p4x#-p3x#) *1.5
				;;;;
				wx#= wx# + (p4x#-p3x#) 	
				wy#= getcourbey#(p4x#) *hlresult#
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch
				;;;;;;::::::::::::::::::::::::::::::::::::::::
				createMpa(wx#,wy#,wz#+prunch,mhlresult#);(((resolxl#+2)*(large#))+xl#))
				;;;;;;::::::::::::::::::::::::::::::::::::::::
				;point6
				u# = u# + (p5x#-p4x#) 
				;;;;
				wx#= wx# + (p5x#-p4x#)
				wy#= getcourbey#(p5x#) *hlresult#
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch
				;point7
				u# = u# + (p6x#-p5x#) 
				;;;;
				wx#= wx# + (p6x#-p5x#) 	
				wy#= getcourbey#(p6x#) *hlresult#
				;;;;
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch
								
			Else If xl# = resolxl#+2;
				u#=EntityX(car,True) +5000 
				v#=Float(large#)*paslarge#
				;;;;
				wz#= Float(large#)*paslarge#
				wx#= EntityX(car,True) +5000 
				wy#= 0 
				AddVertex surf,wx#, wy#, wz#+prunch, u#, v#+prunch
			
			End If		
		Next
	Next
	
	tresolxl# = resolxl# * (nbpoint# +1)+(nbpoint# +1) +1
	For large# = 0 To (resollarge#-1)
		For xl#=0 To (tresolxl#-1)
			AddTriangle surf, (large#*(tresolxl#+1) + xl#), ((large#+1)*(tresolxl#+1) + xl#), ((large#+1)*(tresolxl#+1) + xl#+1)
			AddTriangle surf, ((large#+1)*(tresolxl#+1) + xl#+1), (large#*(tresolxl#+1) + xl#+1), (large#*(tresolxl#+1) + xl#)
		Next
	Next
	
	;UpdateNormals mesh
	
	v_position#= v_position# + Rnd#(0, 0.001)
	if v_position# > 2 
		v_position#=1
	End If 
	;ScaleTexture tvague2,150   ,320   ;
	PositionTexture tvague2,u_position#,v_position# 


	
	EntityTexture mesh,tvague2
	ScaleEntity mesh,1,1,1
	PositionEntity mesh,0,0,0 
	EntityType mesh,WAVE
	EntityAlpha mesh,0.8;alphafond#
	EntityFX mesh,8
	EntityBlend mesh,1
	EntityOrder mesh, 0
	EntityShininess mesh, 1
	
	UpdateNormals mesh
	