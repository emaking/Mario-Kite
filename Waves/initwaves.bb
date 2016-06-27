Const sizeWaveX=1.1
Const pax#=0,p0x#=200*sizeWaveX,p1x# =280*sizeWaveX,p2x# =295*sizeWaveX,p3x# =300,p4x# =308.5*sizeWaveX,p5x# =320*sizeWaveX,p6x# =340*sizeWaveX,p7x# =370*sizeWaveX
Const htsunam = 3
Const pay#=0.3,p0y#=0,p1y#=-0.2*htsunam,p2y# =2.9*htsunam,p3y# =7.15*htsunam,p4y# =8.1*htsunam,p5y# =6*htsunam,p6y# =2.1*htsunam,p7y# =0.3 

Const k0# = (p0y# - pay#)/(p0x# - pax#)
Const cte0# = - k0# * pax# + pay#
Const k1# = (p1y# - p0y#)/(p1x# - p0x#)
Const cte1# = - k1# * p0x# + p0y#	
Const k2# = (p2y# - p1y#)/(p2x# - p1x#)
Const cte2# = - k2# * p1x# + p1y#
Const k3# = (p3y# - p2y#)/(p3x# - p2x#)
Const cte3# = - k3# * p2x# + p2y#
Const k4# = (p4y# - p3y#)/(p4x# - p3x#)
Const cte4# = - k4# * p3x# + p3y#
Const k5# = (p5y# - p4y#)/(p5x# - p4x#)
Const cte5# = - k5# * p4x# + p4y#
Const k6# = (p6y# - p5y#)/(p6x# - p5x#)
Const cte6# = - k6# * p5x# + p5y#	
Const k7# = (p7y# - p6y#)/(p7x# - p6x#)
Const cte7# = - k7# * p6x# + p6y#	

Global roulodif#,roulo#,scorevie=100,score=0,tscore=0,scorebonus=0,scorepitch=0,scoreyaw=0,scoreroll=0,scorelong=0,scorehaut=0,scoresens=1,sens,yawtour,pitchtour,rolltour
response=1
prof#=7





;load vague
tvague=LoadTexture( "_Medias/Wave/water.bmp",1);  wavetex.bmp",1) ;
;ScaleTexture tvague,50,110 ;45,100

ScaleTexture tvague,50,100
 


;load vague
tvague2=LoadTexture( "_Medias/Wave/wavetex.bmp",9)  ; water.bmp",1);
ScaleTexture tvague2,150,320 ;45,100


tvague1=LoadTexture( "_Medias/Wave/water.bmp",9);
ScaleTexture tvague1,45,100

u_position#=1
v_position#=1
 
alphafond# = 0.9

