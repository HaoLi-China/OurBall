package game.ourball.param;

import game.ourball.gui.engine.GameScreen;


public class LevelParam {
	
	public float[] getBalataParam_xl(int level){
		float[] balataParam_xl = null;
		
			switch(level){
			case 0:
				float[] xarray1 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/40,21f/40};
				balataParam_xl = new float[3];
				balataParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/60,11f/40,26f/60,21f/40,41f/60};
				balataParam_xl = new float[6];
				balataParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/60,133f/360,183f/360,233f/360};
				balataParam_xl = new float[5];
				balataParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {BasicParam.sideThickness/(2*GameScreen.screenW),5f/16,5f/8,15f/16,13f/16};
				balataParam_xl = new float[5];
				balataParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {1f/4,3f/7,4f/5,4f/5};
				balataParam_xl = new float[4];
				balataParam_xl=xarray5.clone();
				break;
			}
			
		return balataParam_xl;
	}
	
	public float[] getBalataParam_yl(int level){
		float[] balataParam_yl = null;
		
			switch(level){
			case 0:
				float[] yarray1 = {2f/5,2f/5,2f/5};
				balataParam_yl = new float[3];
				balataParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2 = {2f/5,2f/5,3f/5,3f/5,2f/5,2f/5};
				balataParam_yl = new float[6];
				balataParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {2f/5,2f/5,19f/40,19f/40,19f/40};
				balataParam_yl = new float[5];
				balataParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/2,BasicParam.sideThickness/(2*GameScreen.screenW),7f/8,7f/8,1f/16};
				balataParam_yl = new float[5];
				balataParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {2f/3,1f/40,1f/5,4f/5};
				balataParam_yl = new float[4];
				balataParam_yl=yarray5.clone();
				break;
			}
			
		return balataParam_yl;
	}
	
	public float[] getBalataParam_w(int level){
		float[] balataParam_w = null;
		
			switch(level){
			case 0:
				float[] warray1 = {1f/40,1f/40,1f/40};
				balataParam_w = new float[3];
				balataParam_w=warray1.clone();
				break;
			case 1:
				float[] warray2 = {1f/15,1f/15,1f/15,1f/15,1f/15,1f/15};
				balataParam_w = new float[6];
				balataParam_w=warray2.clone();
				break;
			case 2:
				float[] warray3 = {1f/15,1f/15,1f/20,1f/20,1f/20};
				balataParam_w = new float[5];
				balataParam_w=warray3.clone();
				break;
			case 3:
				float[] warray4 = {1f/40,1f/40,1f/40,1f/20,1f/20};
				balataParam_w = new float[5];
				balataParam_w=warray4.clone();
				break;
			case 4:
				float[] warray5 = {1f/40,1f/40,1f/15,1f/15};
				balataParam_w = new float[4];
				balataParam_w=warray5.clone();
				break;
			}
			
		return balataParam_w;
	}
	
	public float[] getBalataParam_h(int level){
		float[] balataParam_h = null;
		
			switch(level){
			case 0:
				float[] harray1 = {1f/40,1f/40,1f/40};
				balataParam_h = new float[3];
				balataParam_h=harray1.clone();
				break;
			case 1:
				float[] harray2 = {1f/20,1f/20,1f/20,1f/20,1f/20,1f/20};
				balataParam_h = new float[6];
				balataParam_h=harray2.clone();
				break;
			case 2:
				float[] harray3 = {1f/20,1f/20,1f/20,1f/20,1f/20};
				balataParam_h = new float[5];
				balataParam_h=harray3.clone();
				break;
			case 3:
				float[] harray4 = {1f/20,1f/10,1f/10,1f/20,1f/20};
				balataParam_h = new float[5];
				balataParam_h=harray4.clone();
				break;
			case 4:
				float[] harray5 = {1f/6,1f/5,1f/20,1f/20};
				balataParam_h = new float[4];
				balataParam_h=harray5.clone();
				break;
			}
			
		return balataParam_h;
	}
	
	public float[] getBoardParam_xl(int level){
		float[] boardParam_xl = null;
		
			switch(level){
			case 0:
				float[] xarray1 = {2f/8,4f/8,6f/8};
				boardParam_xl = new float[3];
				boardParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {2f/8,4f/8,6f/8};
				boardParam_xl = new float[3];
				boardParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {1f/4,3f/4};
				boardParam_xl = new float[2];
				boardParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {1f/8,5f/16,4f/8,6f/8,7f/8};
				boardParam_xl = new float[5];
				boardParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {1f/10,12f/21};
				boardParam_xl = new float[2];
				boardParam_xl=xarray5.clone();
				break;
			}
			
		return boardParam_xl;
	}
	
	public float[] getBoardParam_yl(int level){
		float[] boardParam_yl = null;
		
			switch(level){
			case 0:
				float[] yarray1 = {1f/4,0,1f/4};
				boardParam_yl = new float[3];
				boardParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2 = {1f/4,0,1f/4};
				boardParam_yl = new float[3];
				boardParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {1f/4,0};
				boardParam_yl = new float[2];
				boardParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/4,2f/4,0,1f/4,2f/4};
				boardParam_yl = new float[5];
				boardParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {3f/5,1f/4};
				boardParam_yl = new float[2];
				boardParam_yl=yarray5.clone();
				break;
			}
			
		return boardParam_yl;
	}
	
	public float[] getBoardParam_w(int level){
		float[] boardParam_w = null;
		
			switch(level){
			case 0:
				float[] warray1 = {1f/40,1f/40,1f/40};
				boardParam_w = new float[3];
				boardParam_w=warray1.clone();
				break;
			case 1:
				float[] warray2 = {1f/40,1f/40,1f/40};
				boardParam_w = new float[3];
				boardParam_w=warray2.clone();
				break;
			case 2:
				float[] warray3 = {1f/40,1f/40};
				boardParam_w = new float[2];
				boardParam_w=warray3.clone();
				break;
			case 3:
				float[] warray4 = {1f/40,1f/40,1f/40,1f/40,5f/40};
				boardParam_w = new float[5];
				boardParam_w=warray4.clone();
				break;
			case 4:
				float[] warray5 = {3f/10,1f/40};
				boardParam_w = new float[2];
				boardParam_w=warray5.clone();
				break;
			}
			
		return boardParam_w;
	}
	
	public float[] getBoardParam_h(int level){
		float[] boardParam_h = null;
		
			switch(level){
			case 0:
				float[] harray1 = {3f/4,3f/4,3f/4};
				boardParam_h = new float[3];
				boardParam_h=harray1.clone();
				break;
			case 1:
				float[] harray2 = {3f/4,3f/4,3f/4};
				boardParam_h = new float[3];
				boardParam_h=harray2.clone();
				break;
			case 2:
				float[] harray3 = {3f/4,3f/4};
				boardParam_h = new float[2];
				boardParam_h=harray3.clone();
				break;
			case 3:
				float[] harray4 = {3f/4,1f/4,3f/4,3f/4,3f/80};
				boardParam_h = new float[5];
				boardParam_h=harray4.clone();
				break;
			case 4:
				float[] harray5 = {1f/20,3f/4};
				boardParam_h = new float[5];
				boardParam_h=harray5.clone();
				break;
			}
			
		return boardParam_h;
	}
	
	public float[] getHoleParam_xl(int level){
		float[] holeParam_xl = null;
		
			switch(level){
			case 0:
				float[] xarray1 = {1f/8,1f/9,3f/8,3f/9,5f/8,5f/9};
				holeParam_xl = new float[6];
				holeParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {1f/12,4f/12,7f/12};
				holeParam_xl = new float[3];
				holeParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {1f/12,11f/40,149f/360,199f/360,2f/3,11f/40,149f/360,199f/360,2f/3,31f/40,9f/10,9f/10};
				holeParam_xl = new float[12];
				holeParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {1f/30,3f/16,4f/16,1f/2,5f/8,13f/16,7f/8};
				holeParam_xl = new float[7];
				holeParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {BasicParam.sideThickness/(2*GameScreen.screenW),1f/11,1f/5,1f/5,3f/10,2f/5,5f/12,12f/19,12f/19,15f/20,9f/10,9f/10,9f/10,9f/10};
				holeParam_xl = new float[14];
				holeParam_xl=xarray5.clone();
				break;
			}
			
		return holeParam_xl;
	}
	
	public float[] getHoleParam_yl(int level){
		float[] holeParam_yl = null;
		
			switch(level){
			case 0:
				float[] yarray1 = {1f/4,2f/4,1f/4,2f/4,1f/4,2f/4};
				holeParam_yl = new float[6];
				holeParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2= {3f/4,1f/4,3f/4};
				holeParam_yl = new float[3];
				holeParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {3f/4,1f/6,1f/6,1f/6,1f/6,4f/6,4f/6,4f/6,4f/6,5f/12,1f/5,4f/6};
				holeParam_yl = new float[12];
				holeParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/30,13f/16,2f/8,5f/6,1f/2,5f/6,1f/4};
				holeParam_yl = new float[7];
				holeParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {13f/48,4f/48,5f/48,21f/48,15f/48,16f/48,5f/6,1f/40,5f/6,5f/12,1f/40,1f/5,4f/6,5f/6};
				holeParam_yl = new float[14];
				holeParam_yl=yarray5.clone();
				break;
			}
			
		return holeParam_yl;
	}
	

	/***********************************************************************/	
	
	public float[] getBalataParam_xl(int playerFlag, int level){
		float[] balataParam_xl = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] xarray1 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/40,21f/40};
				balataParam_xl = new float[3];
				balataParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/60,11f/40,26f/60,21f/40,41f/60};
				balataParam_xl = new float[6];
				balataParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/60,133f/360,183f/360,233f/360};
				balataParam_xl = new float[5];
				balataParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {BasicParam.sideThickness/(2*GameScreen.screenW),5f/16,5f/8,15f/16,13f/16};
				balataParam_xl = new float[5];
				balataParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {1f/4,3f/7,4f/5,4f/5};
				balataParam_xl = new float[4];
				balataParam_xl=xarray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] xarray1 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/40,21f/40};
				balataParam_xl = new float[3];
				balataParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/60,11f/40,26f/60,21f/40,41f/60};
				balataParam_xl = new float[6];
				balataParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {BasicParam.sideThickness/(2*GameScreen.screenW),11f/60,133f/360,183f/360,233f/360};
				balataParam_xl = new float[5];
				balataParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {BasicParam.sideThickness/(2*GameScreen.screenW),5f/16,5f/8,15f/16,13f/16};
				balataParam_xl = new float[5];
				balataParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {1f/4,3f/7,4f/5,4f/5};
				balataParam_xl = new float[4];
				balataParam_xl=xarray5.clone();
				break;
			}
			
			break;
		}
		return balataParam_xl;
	}
	
	public float[] getBalataParam_yl(int playerFlag, int level){
		float[] balataParam_yl = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] yarray1 = {2f/5,2f/5,2f/5};
				balataParam_yl = new float[3];
				balataParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2 = {2f/5,2f/5,3f/5,3f/5,2f/5,2f/5};
				balataParam_yl = new float[6];
				balataParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {2f/5,2f/5,19f/40,19f/40,19f/40};
				balataParam_yl = new float[5];
				balataParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/2,BasicParam.sideThickness/(2*GameScreen.screenW),7f/8,7f/8,1f/16};
				balataParam_yl = new float[5];
				balataParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {2f/3,1f/40,1f/5,4f/5};
				balataParam_yl = new float[4];
				balataParam_yl=yarray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] yarray1 = {2f/5,2f/5,2f/5};
				balataParam_yl = new float[3];
				balataParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2 = {2f/5,2f/5,3f/5,3f/5,2f/5,2f/5};
				balataParam_yl = new float[6];
				balataParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {2f/5,2f/5,19f/40,19f/40,19f/40};
				balataParam_yl = new float[5];
				balataParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/2,BasicParam.sideThickness/(2*GameScreen.screenW),7f/8,7f/8,1f/16};
				balataParam_yl = new float[5];
				balataParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {2f/3,1f/40,1f/5,4f/5};
				balataParam_yl = new float[4];
				balataParam_yl=yarray5.clone();
				break;
			}
			
			break;
		}
		return balataParam_yl;
	}
	
	public float[] getBalataParam_w(int playerFlag, int level){
		float[] balataParam_w = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] warray1 = {1f/40,1f/40,1f/40};
				balataParam_w = new float[3];
				balataParam_w=warray1.clone();
				break;
			case 1:
				float[] warray2 = {1f/15,1f/15,1f/15,1f/15,1f/15,1f/15};
				balataParam_w = new float[6];
				balataParam_w=warray2.clone();
				break;
			case 2:
				float[] warray3 = {1f/15,1f/15,1f/20,1f/20,1f/20};
				balataParam_w = new float[5];
				balataParam_w=warray3.clone();
				break;
			case 3:
				float[] warray4 = {1f/40,1f/40,1f/40,1f/20,1f/20};
				balataParam_w = new float[5];
				balataParam_w=warray4.clone();
				break;
			case 4:
				float[] warray5 = {1f/40,1f/40,1f/15,1f/15};
				balataParam_w = new float[4];
				balataParam_w=warray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] warray1 = {1f/40,1f/40,1f/40};
				balataParam_w = new float[3];
				balataParam_w=warray1.clone();
				break;
			case 1:
				float[] warray2 = {1f/15,1f/15,1f/15,1f/15,1f/15,1f/15};
				balataParam_w = new float[6];
				balataParam_w=warray2.clone();
				break;
			case 2:
				float[] warray3 = {1f/15,1f/15,1f/20,1f/20,1f/20};
				balataParam_w = new float[5];
				balataParam_w=warray3.clone();
				break;
			case 3:
				float[] warray4 = {1f/40,1f/40,1f/40,1f/20,1f/20};
				balataParam_w = new float[5];
				balataParam_w=warray4.clone();
				break;
			case 4:
				float[] warray5 = {1f/40,1f/40,1f/15,1f/15};
				balataParam_w = new float[4];
				balataParam_w=warray5.clone();
				break;
			}
			
			break;
		}
		return balataParam_w;
	}
	
	public float[] getBalataParam_h(int playerFlag, int level){
		float[] balataParam_h = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] harray1 = {1f/40,1f/40,1f/40};
				balataParam_h = new float[3];
				balataParam_h=harray1.clone();
				break;
			case 1:
				float[] harray2 = {1f/20,1f/20,1f/20,1f/20,1f/20,1f/20};
				balataParam_h = new float[6];
				balataParam_h=harray2.clone();
				break;
			case 2:
				float[] harray3 = {1f/20,1f/20,1f/20,1f/20,1f/20};
				balataParam_h = new float[5];
				balataParam_h=harray3.clone();
				break;
			case 3:
				float[] harray4 = {1f/20,1f/10,1f/10,1f/20,1f/20};
				balataParam_h = new float[5];
				balataParam_h=harray4.clone();
				break;
			case 4:
				float[] harray5 = {1f/6,1f/5,1f/20,1f/20};
				balataParam_h = new float[4];
				balataParam_h=harray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] harray1 = {1f/40,1f/40,1f/40};
				balataParam_h = new float[3];
				balataParam_h=harray1.clone();
				break;
			case 1:
				float[] harray2 = {1f/20,1f/20,1f/20,1f/20,1f/20,1f/20};
				balataParam_h = new float[6];
				balataParam_h=harray2.clone();
				break;
			case 2:
				float[] harray3 = {1f/20,1f/20,1f/20,1f/20,1f/20};
				balataParam_h = new float[5];
				balataParam_h=harray3.clone();
				break;
			case 3:
				float[] harray4 = {1f/20,1f/10,1f/10,1f/20,1f/20};
				balataParam_h = new float[5];
				balataParam_h=harray4.clone();
				break;
			case 4:
				float[] harray5 = {1f/6,1f/5,1f/20,1f/20};
				balataParam_h = new float[4];
				balataParam_h=harray5.clone();
				break;
			}
			
			break;
		}
		return balataParam_h;
	}
	
	public float[] getBoardParam_xl(int playerFlag, int level){
		float[] boardParam_xl = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] xarray1 = {2f/8,4f/8,6f/8};
				boardParam_xl = new float[3];
				boardParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {2f/8,4f/8,6f/8};
				boardParam_xl = new float[3];
				boardParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {1f/4,3f/4};
				boardParam_xl = new float[2];
				boardParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {1f/8,5f/16,4f/8,6f/8,7f/8};
				boardParam_xl = new float[5];
				boardParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {1f/10,12f/21};
				boardParam_xl = new float[2];
				boardParam_xl=xarray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] xarray1 = {2f/8,4f/8,6f/8};
				boardParam_xl = new float[3];
				boardParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {2f/8,4f/8,6f/8};
				boardParam_xl = new float[3];
				boardParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {1f/4,3f/4};
				boardParam_xl = new float[2];
				boardParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {1f/8,5f/16,4f/8,6f/8,7f/8};
				boardParam_xl = new float[5];
				boardParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {1f/10,12f/21};
				boardParam_xl = new float[2];
				boardParam_xl=xarray5.clone();
				break;
			}
			
			break;
		}
		return boardParam_xl;
	}
	
	public float[] getBoardParam_yl(int playerFlag, int level){
		float[] boardParam_yl = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] yarray1 = {1f/4,0,1f/4};
				boardParam_yl = new float[3];
				boardParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2 = {1f/4,0,1f/4};
				boardParam_yl = new float[3];
				boardParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {1f/4,0};
				boardParam_yl = new float[2];
				boardParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/4,2f/4,0,1f/4,2f/4};
				boardParam_yl = new float[5];
				boardParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {3f/5,1f/4};
				boardParam_yl = new float[2];
				boardParam_yl=yarray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] yarray1 = {1f/4,0,1f/4};
				boardParam_yl = new float[3];
				boardParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2 = {1f/4,0,1f/4};
				boardParam_yl = new float[3];
				boardParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {1f/4,0};
				boardParam_yl = new float[2];
				boardParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/4,2f/4,0,1f/4,2f/4};
				boardParam_yl = new float[5];
				boardParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {3f/5,1f/4};
				boardParam_yl = new float[2];
				boardParam_yl=yarray5.clone();
				break;
			}
			
			break;
		}
		return boardParam_yl;
	}
	
	public float[] getBoardParam_w(int playerFlag, int level){
		float[] boardParam_w = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] warray1 = {1f/40,1f/40,1f/40};
				boardParam_w = new float[3];
				boardParam_w=warray1.clone();
				break;
			case 1:
				float[] warray2 = {1f/40,1f/40,1f/40};
				boardParam_w = new float[3];
				boardParam_w=warray2.clone();
				break;
			case 2:
				float[] warray3 = {1f/40,1f/40};
				boardParam_w = new float[2];
				boardParam_w=warray3.clone();
				break;
			case 3:
				float[] warray4 = {1f/40,1f/40,1f/40,1f/40,5f/40};
				boardParam_w = new float[5];
				boardParam_w=warray4.clone();
				break;
			case 4:
				float[] warray5 = {3f/10,1f/40};
				boardParam_w = new float[2];
				boardParam_w=warray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] warray1 = {1f/40,1f/40,1f/40};
				boardParam_w = new float[3];
				boardParam_w=warray1.clone();
				break;
			case 1:
				float[] warray2 = {1f/40,1f/40,1f/40};
				boardParam_w = new float[3];
				boardParam_w=warray2.clone();
				break;
			case 2:
				float[] warray3 = {1f/40,1f/40};
				boardParam_w = new float[2];
				boardParam_w=warray3.clone();
				break;
			case 3:
				float[] warray4 = {1f/40,1f/40,1f/40,1f/40,5f/40};
				boardParam_w = new float[5];
				boardParam_w=warray4.clone();
				break;
			case 4:
				float[] warray5 = {3f/10,1f/40};
				boardParam_w = new float[2];
				boardParam_w=warray5.clone();
				break;
			}
			
			break;
		}
		return boardParam_w;
	}
	
	public float[] getBoardParam_h(int playerFlag, int level){
		float[] boardParam_h = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] harray1 = {3f/4,3f/4,3f/4};
				boardParam_h = new float[3];
				boardParam_h=harray1.clone();
				break;
			case 1:
				float[] harray2 = {3f/4,3f/4,3f/4};
				boardParam_h = new float[3];
				boardParam_h=harray2.clone();
				break;
			case 2:
				float[] harray3 = {3f/4,3f/4};
				boardParam_h = new float[2];
				boardParam_h=harray3.clone();
				break;
			case 3:
				float[] harray4 = {3f/4,1f/4,3f/4,3f/4,3f/80};
				boardParam_h = new float[5];
				boardParam_h=harray4.clone();
				break;
			case 4:
				float[] harray5 = {1f/20,3f/4};
				boardParam_h = new float[5];
				boardParam_h=harray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] harray1 = {3f/4,3f/4,3f/4};
				boardParam_h = new float[3];
				boardParam_h=harray1.clone();
				break;
			case 1:
				float[] harray2 = {3f/4,3f/4,3f/4};
				boardParam_h = new float[3];
				boardParam_h=harray2.clone();
				break;
			case 2:
				float[] harray3 = {3f/4,3f/4};
				boardParam_h = new float[2];
				boardParam_h=harray3.clone();
				break;
			case 3:
				float[] harray4 = {3f/4,1f/4,3f/4,3f/4,3f/80};
				boardParam_h = new float[5];
				boardParam_h=harray4.clone();
				break;
			case 4:
				float[] harray5 = {1f/20,3f/4};
				boardParam_h = new float[5];
				boardParam_h=harray5.clone();
				break;
			}
			
			break;
		}
		return boardParam_h;
	}
	
	public float[] getHoleParam_xl(int playerFlag, int level){
		float[] holeParam_xl = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] xarray1 = {1f/8,1f/9,3f/8,3f/9,5f/8,5f/9,7f/8,7f/9};
				holeParam_xl = new float[8];
				holeParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {1f/12,4f/12,7f/12,9f/10,9f/10,9f/10};
				holeParam_xl = new float[6];
				holeParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {1f/12,11f/40,149f/360,199f/360,2f/3,11f/40,149f/360,199f/360,2f/3,31f/40,17f/20,9f/10,9f/10};
				holeParam_xl = new float[13];
				holeParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {1f/30,3f/16,4f/16,1f/2,5f/8,13f/16,7f/8};
				holeParam_xl = new float[7];
				holeParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {BasicParam.sideThickness/(2*GameScreen.screenW),1f/11,1f/5,1f/5,3f/10,2f/5,5f/12,12f/19,12f/19,15f/20,9f/10,9f/10,9f/10,9f/10};
				holeParam_xl = new float[14];
				holeParam_xl=xarray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] xarray1 = {1f/8,1f/9,3f/8,3f/9,5f/8,5f/9};
				holeParam_xl = new float[6];
				holeParam_xl=xarray1.clone();
				break;
			case 1:
				float[] xarray2 = {1f/12,4f/12,7f/12};
				holeParam_xl = new float[3];
				holeParam_xl=xarray2.clone();
				break;
			case 2:
				float[] xarray3 = {1f/12,11f/40,149f/360,199f/360,2f/3,11f/40,149f/360,199f/360,2f/3,31f/40,9f/10,9f/10};
				holeParam_xl = new float[12];
				holeParam_xl=xarray3.clone();
				break;
			case 3:
				float[] xarray4 = {1f/30,3f/16,4f/16,1f/2,5f/8,13f/16,7f/8};
				holeParam_xl = new float[7];
				holeParam_xl=xarray4.clone();
				break;
			case 4:
				float[] xarray5 = {BasicParam.sideThickness/(2*GameScreen.screenW),1f/11,1f/5,1f/5,3f/10,2f/5,5f/12,12f/19,12f/19,15f/20,9f/10,9f/10,9f/10,9f/10};
				holeParam_xl = new float[14];
				holeParam_xl=xarray5.clone();
				break;
			}
			
			break;
		}
		return holeParam_xl;
	}
	
	public float[] getHoleParam_yl(int playerFlag, int level){
		float[] holeParam_yl = null;
		switch(playerFlag){
		case 0:
			switch(level){
			case 0:
				float[] yarray1 = {1f/4,2f/4,1f/4,2f/4,1f/4,2f/4,1f/4,2f/4};
				holeParam_yl = new float[8];
				holeParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2= {3f/4,1f/4,3f/4,1f/40,2f/6,4f/6};
				holeParam_yl = new float[6];
				holeParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {3f/4,1f/6,1f/6,1f/6,1f/6,4f/6,4f/6,4f/6,4f/6,5f/12,5f/6,1f/5,4f/6};
				holeParam_yl = new float[13];
				holeParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/30,13f/16,2f/8,5f/6,1f/2,5f/6,1f/4};
				holeParam_yl = new float[7];
				holeParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {13f/48,4f/48,5f/48,21f/48,15f/48,16f/48,5f/6,1f/40,5f/6,5f/12,1f/40,1f/5,4f/6,5f/6};
				holeParam_yl = new float[14];
				holeParam_yl=yarray5.clone();
				break;
			}
			break;
		case 1:
			switch(level){
			case 0:
				float[] yarray1 = {1f/4,2f/4,1f/4,2f/4,1f/4,2f/4};
				holeParam_yl = new float[6];
				holeParam_yl=yarray1.clone();
				break;
			case 1:
				float[] yarray2= {3f/4,1f/4,3f/4};
				holeParam_yl = new float[3];
				holeParam_yl=yarray2.clone();
				break;
			case 2:
				float[] yarray3 = {3f/4,1f/6,1f/6,1f/6,1f/6,4f/6,4f/6,4f/6,4f/6,5f/12,1f/5,4f/6};
				holeParam_yl = new float[12];
				holeParam_yl=yarray3.clone();
				break;
			case 3:
				float[] yarray4 = {1f/30,13f/16,2f/8,5f/6,1f/2,5f/6,1f/4};
				holeParam_yl = new float[7];
				holeParam_yl=yarray4.clone();
				break;
			case 4:
				float[] yarray5 = {13f/48,4f/48,5f/48,21f/48,15f/48,16f/48,5f/6,1f/40,5f/6,5f/12,1f/40,1f/5,4f/6,5f/6};
				holeParam_yl = new float[14];
				holeParam_yl=yarray5.clone();
				break;
			}
			
			break;
		}
		return holeParam_yl;
	}
	
	public float getFinalHoleParam_xl(int level){
		float xLocationParam=0;
		switch(level){
		case 0:
			xLocationParam = 5f/6;
			break;
		case 1:
			xLocationParam = 6f/7;
			break;
		case 2:
			xLocationParam = 9f/10;
			break;
		case 3:
			xLocationParam = 8f/9;
			break;
		case 4:
			xLocationParam = 9f/10;
			break;
		}
		return xLocationParam;
	}
	
	public float getFinalHoleParam_yl(int level){
		float yLocationParam=0;
		switch(level){
		case 0:
			yLocationParam=1f/6;
			break;
		case 1:
			yLocationParam=3f/5;
			break;
		case 2:
			yLocationParam=5f/12;
			break;
		case 3:
			yLocationParam=4f/7;
			break;
		case 4:
			yLocationParam=5f/12;
			break;
		}
		return yLocationParam;
	}
	
	//玩家一障碍物横坐标数组
	//玩家一障碍物纵坐标数组
	//玩家一障碍物长度数组
	//玩家一障碍物宽度数组
	
	//玩家一小洞横坐标数组
	//玩家一小洞纵坐标数组
	//玩家一小洞长度数组
	//玩家一小洞宽度数组
	
	//玩家二障碍物横坐标数组
	//玩家二障碍物纵坐标数组
	//玩家二障碍物长度数组
	//玩家二障碍物宽度数组
	
	//玩家二小洞横坐标数组
	//玩家二小洞纵坐标数组
	//玩家二小洞长度数组
	//玩家二小洞宽度数组
	
	//玩家二终点横坐标数组
	//玩家二终点纵坐标数组
	//玩家二终点长度数组
	//玩家二终点宽度数组

}
