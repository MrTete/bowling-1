import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private List<Pair<Integer,Integer>> scores;

    private int nbMaxSets;


    public BowlingGame(int nbMaxSets)
    {
        this.nbMaxSets = nbMaxSets;
        scores = new ArrayList<>(this.nbMaxSets);
    }

    @Override
	public String toString() {
		return "" + scores + " " + this.getSum();
	}

	public BowlingGame(List<Pair<Integer,Integer>> score, int nbMaxSets)
    {
        scores = score;
        this.nbMaxSets = nbMaxSets;
    }

    public int getSum()
    {
        if (scores == null)
            throw new IllegalArgumentException("The parameter SCORES is null.");

        boolean isStrike;
        boolean isSpare;

        int somme = 0;
        for (int i = 0; i < scores.size(); ++i)
        {
            if(i < nbMaxSets)
            {
                somme += scores.get(i).getItem1() + scores.get(i).getItem2();
                isStrike = scores.get(i).getItem1() == 10;
                isSpare = scores.get(i).getItem1() + scores.get(i).getItem2() == 10 && !isStrike;

                if (isStrike && i + 1 < scores.size())
                {
                    somme += scores.get(i + 1).getItem1();
                    if (isStrike(scores.get(i + 1)) && i + 2 < scores.size())
                        somme += scores.get(i + 2).getItem1();
                    else if (scores.get(i + 1).getItem2() != 0)
                        somme += scores.get(i + 1).getItem2();

                }
                else if (isSpare && i + 1 < scores.size())
                {
                    somme += scores.get(i + 1).getItem1();
                }
            }

        }

        return somme;
    }

    public boolean addScore(Pair<Integer,Integer> score)
    {
        if (score.getItem1() + score.getItem2() < 0)
            return false;
        if (score.getItem1() + score.getItem2() > 10)
            return false;
        if (scores.size() == nbMaxSets)
        {
            Pair<Integer, Integer> last = scores.get(scores.size()-1);
            if (isStrike(last))
            {
                scores.add(score);
                return true;
            }
            else if (isSpare(last))
            {
                if (score.getItem2() == 0)
                {
                    scores.add(score);
                    return true;
                }
                else
                    return false;

            }
            return false;

        }
        else if (scores.size() == nbMaxSets + 1)
        {
            Pair<Integer, Integer> last = scores.get(scores.size()-1);
            if (isStrike(last))
            {
                scores.add(score);
                return true;
            }
            else
                return false;
        }
        else if (scores.size() >= nbMaxSets + 2)
            return false;

        scores.add(score);
        return true;
    }
    
    public boolean canPlay() {
    	
    	if (scores.size() == nbMaxSets)
        {
    		Pair<Integer, Integer> last = scores.get(scores.size()-1);
            if (isStrike(last))
                return true;
            else if (isSpare(last))
               return true;

            return false;
        }
        else if (scores.size() == nbMaxSets + 1)
        {
        	Pair<Integer, Integer> last = scores.get(scores.size()-1);
            if (isStrike(last))
                return true;
            else
                return false;
        }
        else if (scores.size() >= nbMaxSets + 2)
            return false;

        return true;
    }

    private boolean isStrike(Pair<Integer,Integer> shot)
    {
        return (shot.getItem1() == 10 && shot.getItem2() == 0) ;
    }
    private boolean isSpare(Pair<Integer, Integer> shot)
    {
        return (shot.getItem1() + shot.getItem2() == 10 && !isStrike(shot));
    }
}
