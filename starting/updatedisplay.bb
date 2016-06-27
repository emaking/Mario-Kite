Color 10,10,255
	blob$="yawcar "+timet+" // "+tournicoti+"  ////yawkite"+EntityYaw(pkiteObjc,True)
	strfigur$="Forme ["+scorevie+"]   Bonus [ X"+scorebonus+" * "+scoresens+" ]   Rotation [ "+(yawtour*90)+" ° ]   Loop [ "+(pitchtour*90)+"° ]    Vrille [ "+(rolltour*90)+"° ]   hauteur [ "+scorehaut+" m ]   Longueur [ "+scorelong+"m ]  Sens [ "+scoresens+" ] =   + " +score +" pts "   
	 
	
	strmessage$="alphafond = "+alphafond;lightx="+EntityX(l,True)+ " >>lighty="+ EntityY(l,True)+ " >>lightz="+EntityZ(l,True)
	
	;strmessage$="lightx="+EntityPitch(l,True)+ " >>lighty="+ EntityYaw(l,True)+ " >>lightz="+EntityRoll(l,True)
	
	
	;strmessage$="angle Barre="+angleBarre
		
	;strmessage$+=" zone"+blob$+" =====  x"+EntityX(car,True)+"z"+EntityZ(car,True)+" ... message perso .... speed:"+speed#+"Alpha:"+alphafond#+"R"+CRed+"G"+CGreen+"B"+CBlue


	strmessage$=strmessage$+" MESS : "+ strmess$
	
	
	
	SetFont fntBeachB
	;Text largeurE/2,(HauteurE*3/4)+(HauteurE/4)*2.5/5,tscore+" pts",True,False
		
	SetFont fntArialB
	;Text largeurE/2,(HauteurE*3/4)+(HauteurE/4)*3.5/5, strfigur$,True,False
	
	SetFont fntArial
;	Text largeurE/2,(HauteurE*3/4)+(HauteurE/4)*4.2/5, strmessage$,True,False
	
	
	