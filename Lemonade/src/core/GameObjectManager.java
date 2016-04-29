package core;

import java.util.ArrayList;

public class GameObjectManager {
	
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public GameObjectManager (){

	}
	
	public void Init (){
		for (GameObject g : objects){
			g.Init();
		}
	}
	
	public void Update (){
		for (GameObject g : objects)
			g.Update();
	}
	
	public void Render (){
		for (GameObject g : objects)
			g.Render();
	}
	
	public void Destroy (){
		for (GameObject g : objects)
			g.Destroy();
	}
	
	/*
	 * 
	 */
	
	public void addGameObject (GameObject g){

		if (g == null)
		{
			System.err.println("[Err]: GameObect-ul nu poate fi adugat deoarece este null.");
			return;
		}
		
		if (g.name == null)
		{
			System.err.println("[Err]: GameObject-ul nu pate fi adaugat deoarece nu are nume.");
			return;
		}

		int index = this.existsGameObject(g.name);
		if (index != -1)
			System.err.println("[Err]: Exista deja un GameObject cu Numele: " + g.name);
		else{
			g.Init();
			objects.add(g);
		}
	}
	
	public void removeGameObject (String name){
		int index = this.existsGameObject(name);
		if (index != -1){		
			objects.get(index).Destroy();
			this.objects.remove(index);
		}else {
			System.err.println("[Err]: GameObject-ul cu Numele: " + name + ", nu exista");
		}
	}
	
	public void removeGameObjectsWithTag (String tag){
		for (int i=0; i<objects.size(); i++)
		{
			if (objects.get(i).tag.equals(tag))
			{
				objects.remove(i);
			}
		}
	}
	
	public void removeGameObject (GameObject g){
		int index = this.existsGameObject(g.name);
		if (index != -1){		
			objects.get(index).Destroy();
			this.objects.remove(g);
		}else {
			System.err.println("[Err]: GameObject-ul cu Numele: " + g.name + ", nu exista");
		}
	}
	
	public int existsGameObject (String name){
		for (int i = 0; i<objects.size(); i++){
			if (objects.get(i).name.equals(name))
				return i;
		}
		
		return -1;
	}
	
	public int size (){
		return this.objects.size();
	}
	
	public GameObject getGameObject (int index){
		return this.objects.get(index);
	}
	
	/**
	 * 
	 * DEBUG ONLY
	 * 
	 */
	
	public void PrintGameObjectToConsloe (){
		for (int i=0; i<this.objects.size(); i++)
			System.out.println(objects.get(i).name + " Position: " + this.objects.get(i).position.x + " " + this.objects.get(i).position.y + 
					" Size: " + objects.get(i).scale.x + " " + objects.get(i).scale.y);
	}
	
}
