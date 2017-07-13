package solver;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import model.Position2D;
import model.entities.Box;
import model.entities.Figure;
import model.levels.Level;
import plan.AndPredicate;
import plan.NotPredicate;
import plan.PlanAction;
import plan.Predicate;
import search.Action;
import search.BFS;

public class MoveFigure extends CommonAction implements PlanAction<Position2D> {

	public MoveFigure(Level level, Figure figure, Position2D position) {
		super(3);
		params[0] = level;
		params[1] = figure;
		params[2] = position;
	}

	@Override
	public boolean isAtomic() {
		return false;
	}

	@Override
	public List<Predicate> getPreconditions() {
		List<Predicate> preConditions = new LinkedList<Predicate>();
		FigurePathSearchable searchable = new FigurePathSearchable((Level)params[0], (Figure)params[1], (Position2D)params[2]);
		playerActions = new BFS().search(searchable);
		preConditions.add(new NotPredicate(new BoxAtPredicate((Position2D)params[2])));
		return preConditions;
	}

	@Override
	public AndPredicate getEffect() {
		return new AndPredicate(new FigureAtPredicate((Position2D)params[2]),new NotPredicate(new FigureAtPredicate(((Figure)params[1]).getPosition())));
	}


}
