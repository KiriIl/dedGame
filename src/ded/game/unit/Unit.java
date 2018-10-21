package ded.game.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import ded.game.skills.Skill;
import framework.game.GameObject;

public abstract class Unit extends GameObject{
	
	HashMap<String, Skill> skill = new HashMap<String, Skill>();
	
	public  int maxHP = 0;
	public int HP = 0;
	protected int defSpeed = 0;
	public int speed = 0;
	
	public float inBattleX = 0;
	public float inBattleY = 0;
	
	public Unit(float x, float y, int maxHP, int defSpeed) {
		super(x, y, 0, 0);
		this.maxHP = maxHP;
		this.HP = maxHP;
		this.defSpeed = defSpeed;
		this.speed = defSpeed;
	}
	
	public void cast(String skillName, Unit target) {
		skill.get(skillName).cast(this, target);
	}
	
	public ArrayList<String> getSkillList(){
		ArrayList<String> _skills = new ArrayList<String>();
		for(Entry<String, Skill> entry : skill.entrySet()) {
		    _skills.add(entry.getKey());
		}
		return _skills;
	}
	
}