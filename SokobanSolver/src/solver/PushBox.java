package solver;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import fail.AndPredicate;
import fail.IPredicate;
import model.Position2D;
import model.entities.Box;
import model.levels.Level;
import plan.Clause;
import plan.PlanAction;
import plan.Predicate;
import plan.PredicateType;
import search.BFS;
import search.Searcher;

public class PushBox extends SokobanAction implements PlanAction<Position2D> {

	public PushBox(LevelSearcher level,String id, Position2D value) {
		super(level, PredicateType.Push, "Box", id, value);
	}
	
	@Override
	public List<Predicate<Position2D>> getPreconditions() {
		List<Predicate<Position2D>> preConditions = new LinkedList<>();
		path = level.searchPushPath(entity, id,value);
		if(!path.thereIsPath())
			preConditions.add(new Predicate<Position2D>(PredicateType.NoSolution,"?","?",null));
		else
		{
			for(Position2D[] pos : level.getNodes(path))
			{
				preConditions.add(new Predicate<Position2D>(PredicateType.EntityAt,"Figure","0",pos[1]));
				preConditions.add(new Predicate<Position2D>(PredicateType.ReadyToPush,"Box","0",pos[0]));
			}
		}
		
		return preConditions;
	}

	@Override
	public Clause<Position2D> getEffect() {
		Clause<Position2D> effects = new Clause<>();
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Nothing","0",path.getFirstState().getState()));
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Nothing","0",path.getSecondState().getState()));
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Box",id,path.getLastState().getState()));
		effects.add(new Predicate<Position2D>(PredicateType.EntityAt,"Figure","0",path.getBeforeLastState().getState()));
		return effects;
	}



}
