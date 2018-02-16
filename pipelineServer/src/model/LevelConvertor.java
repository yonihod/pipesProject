package model;

public class LevelConvertor {

	public char convertFromObjectToChar(GameObject go) {
		if(go instanceof StartObject) { return 's';}
		else if(go instanceof GoalObject) { return 'g';}
		else if(go instanceof BlankObject) { return ' ';}
		else if(go instanceof HyphenObject) { return '-';}
		else if(go instanceof VerticalBarObject) { return '|';}
		else if(go instanceof SevenObject) { return '7';}
		else if(go instanceof LObject) { return 'L';}
		else if(go instanceof JObject) { return 'J';}
		else if(go instanceof FObject) { return 'F';}
		else {return ' ';}
	}
	
	public GameObject convertFromCharToObject(char c) {
		GameObject go=null;
		switch(c) {
		case 's':{
			go=new StartObject();
			break;
		}
		case 'g':{
			go=new GoalObject();
			break;
		}
		case ' ':{
			go=new BlankObject();
			break;
		}
		case '-':{
			go=new HyphenObject();
			break;
		}
		case '|':{
			go=new VerticalBarObject();
			break;
		}
		case '7':{
			go=new SevenObject();
			break;
		}
		case 'L':{
			go=new LObject();
			break;
		}
		case 'J':{
			go=new JObject();
			break;
		}
		case 'F':{
			go=new FObject();
			break;
		}
		default:
			break;
		}
	return go;
	}
	
}
