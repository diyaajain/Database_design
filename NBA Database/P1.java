
import java.io.*;
import java.util.*;
public class P1 {
	
	/* Define data structures for holding the data here */
    ArrayList<Coach>coaches;
    ArrayList<Team>teams;

    public P1(ArrayList<Coach> coaches) {
        this.coaches = coaches;
    }

    public P1() {
        /* initialize the data structures */
        coaches = new ArrayList<Coach>();
        teams = new ArrayList<Team>();
    }
    
    public void run() {
        CommandParser parser = new CommandParser();

        System.out.println("The mini-DB of NBA coaches and teams");
        System.out.println("Please enter a command.  Enter 'help' for a list of commands.");
        System.out.println();
        System.out.print("> "); 
        
        Command cmd = null;
        while ((cmd = parser.fetchCommand()) != null) {
            //System.out.println(cmd);
            
            boolean result=false;
            
            if (cmd.getCommand().equals("help")) {
                result = doHelp();

		/* You need to implement all the following commands */

            } 
        else if (cmd.getCommand().equals("add_coach")) {
            String[] parameters = cmd.getParameters();
            Coach c = new Coach(parameters[0], Integer.parseInt(parameters[1]), 0 , parameters[3],
            parameters[4], Integer.parseInt(parameters[5]), Integer.parseInt(parameters[6]), Integer.parseInt(parameters[7]), Integer.parseInt(parameters[8])
            ,parameters[9]);
            coaches.add(c);   
	    }
        else if (cmd.getCommand().equals("add_team")) {
        	String[] parameters = cmd.getParameters();
            Team t = new Team (parameters[0],parameters[1], parameters[2], parameters[3]);
            teams.add(t);
		}
        else if (cmd.getCommand().equals("print_coaches")) {
            for(int i = 0; i<coaches.size();i++)
            {
                System.out.println(coaches.get(i));
            }
	   	}
        else if (cmd.getCommand().equals("print_teams")) {
            for(int i = 0; i<teams.size();i++)
            {
                System.out.println(teams.get(i));
            }
		} 
        else if (cmd.getCommand().equals("coaches_by_name")) {
            String parameters[] = cmd.getParameters();
            for(int i = 0; i<coaches.size();i++)
            {
                if (coaches.get(i).getLast_name().trim().equalsIgnoreCase(parameters[0].replace("+", "")))
                {
                    System.out.println(coaches.get(i));
                }
            }
		} 
        else if (cmd.getCommand().equals("teams_by_city")) {
            String parameters[] = cmd.getParameters();
            for(int i = 0; i<teams.size();i++)
            {
                if (teams.get(i).getLocation().trim().equals(parameters[0]))
                {
                    System.out.println(teams.get(i));
                }
            }
		}
        else if (cmd.getCommand().equals("load_coaches")) 
        {
            String parameters[] = cmd.getParameters();
            Scanner infile;
            try
            {
                infile = new Scanner(new File(parameters[0]));

                String line;
                String[] values;

                if (infile.hasNextLine())
                    line = infile.nextLine();
                
                while (infile.hasNextLine())
                {
                    line = infile.nextLine();
                    values = line.split(",");
                    if (values.length == 10)
                    {
                        Coach c = new Coach(values[0], Integer.parseInt(values[1]), 0, values[3], values[4],
                        Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8])
                        ,(values[9]));
                        coaches.add(c);
                    }

                }
                infile.close();
            }
            catch(FileNotFoundException e)
            {
                System.out.println(parameters[0] + " file does not exist!");
            }
        }
        else if (cmd.getCommand().equals("load_team")) 
        {
         
            String parameters[] = cmd.getParameters();
            Scanner infile;
            try
            {
                infile = new Scanner(new File(parameters[0]));

                String line;
                String[] values;

                if (infile.hasNextLine())
                    line = infile.nextLine();
                
                while (infile.hasNextLine())
                {
                    line = infile.nextLine();
                    values = line.split(",");
                    if (values.length == 4)
                    {
                        Team t = new Team(values[0], values[1], values[2], values[3]);
                        teams.add(t);
                    }
                }
                infile.close();
            }
            catch(FileNotFoundException e)
            {
                System.out.println(parameters[0] + "file does not exist!");
            }
        }
            else if (cmd.getCommand().equals("best_coach")) 
            {
            String parameters[] = cmd.getParameters();
            int bestCoachNetWins = 0;
            int netWins;
            Coach c;
            
            for (int i = 0; i<coaches.size(); i++)
            {
                c = coaches.get(i);
    
                if (c.getYear() == Integer.parseInt(parameters[0]))
                {
                    netWins = (c.getSeason_win() - c.getSeason_loss() + c.getPlayoff_win() - c.getPlayoff_loss());
    
                    if(netWins > bestCoachNetWins)
                    {
                        bestCoachNetWins = netWins;
                    }
                }
            }
            for (int i =0; i<coaches.size(); i++)
            {
                c = coaches.get(i);
    
                if (c.getYear() == Integer.parseInt(parameters[0]))
                {
                    netWins = (c.getSeason_win() - c.getSeason_loss() + c.getPlayoff_win() - c.getPlayoff_loss());
                    if(netWins == bestCoachNetWins)
                    {
                        System.out.println(c.getFirst_name() + " " + c.getLast_name());
                    }
                }
            }
        }    
        else if (cmd.getCommand().equals("search_coaches")) 
        {
            String[] parameters = cmd.getParameters();
            String[] tokens = parameters[0].split("=");
            String field = tokens[0];
            String value = tokens[1];
            
            for (int i = 0; i<coaches.size(); i++)
            {
                Coach c = coaches.get(i);

                if (field.equalsIgnoreCase("coachid") && value.equalsIgnoreCase(c.getCoach_ID()))
                {
                    System.out.println(c.getFirst_name()+ "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("year") && Integer.parseInt(value) == c.getYear());
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("yr_order") && Integer.parseInt(value) == c.getYr_order());
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("firstname") && value.equalsIgnoreCase(c.getFirst_name()));
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("lastname") && value.equalsIgnoreCase(c.getLast_name()));
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("season_win") && Integer.parseInt(value) == c.getSeason_win());
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("season_loss") && Integer.parseInt(value) == c.getSeason_loss());
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("playoff_win") && Integer.parseInt(value) == c.getPlayoff_win());
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("playoff_loss") && Integer.parseInt(value) == c.getPlayoff_loss());
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
                if(field.equalsIgnoreCase("team") && value.equalsIgnoreCase(c.getTeam()));
                {
                    System.out.println(c.getFirst_name() + "" + c.getLast_name());
                }
            }
		}
        else if (cmd.getCommand().equals("exit")) {
			System.out.println("Leaving the database, goodbye!");
			break;
		} else if (cmd.getCommand().equals("")) 
        {
		} 
        else 
        {
			System.out.println("Invalid Command, try again!");
        } 
            
	    if (result) 
        {
                // ...
        }

            System.out.print("> "); 
        }        
    }
    
    private boolean doHelp() {
        System.out.println("add_coach ID SEASON FIRST_NAME LAST_NAME SEASON_WIN "); 
	System.out.println("          SEASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
        System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
        System.out.println("print_coaches - print a listing of all coaches");
        System.out.println("print_teams - print a listing of all teams");
        System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
        System.out.println("teams_by_city CITY - list the teams in the specified city");
	    System.out.println("load_coach FILENAME - bulk load of coach info from a file");
        System.out.println("load_team FILENAME - bulk load of team info from a file");
        System.out.println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
        System.out.println("search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
		System.out.println("exit - quit the program");        
        return true;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) 
    {
        new P1().run();
    }
    
    private class Coach
    {
        private String coach_ID;
        private int year;
        private int yr_order;
        private String first_name;
        private String last_name;
        private int season_win;
        private int season_loss;
        private int playoff_win;
        private int playoff_loss;
        private String team;

        public Coach(String coach_ID, int year, int yr_order ,String first_name, String last_name,
        int season_win, int season_loss, int playoff_win, int playoff_loss, String team)
        {
            this.coach_ID = coach_ID;
            this.year = year;
            this.yr_order = yr_order;
            this.first_name = first_name;
            this.last_name = last_name;
            this.season_win = season_win;
            this.season_loss = season_loss;
            this.playoff_win = playoff_win;
            this.playoff_loss = playoff_loss;
            this.team = team;
        }
        public String getCoach_ID()
        {
            return coach_ID;
        }
        public int getYear()
        {
            return year;
        }
        public int getYr_order()
        {
            return yr_order;
        }
        public String getFirst_name()
        {
            return first_name;
        }
        public String getLast_name()
        {
            return last_name;
        }
        public int getSeason_win()
        {
            return season_win;
        }
        public int getSeason_loss()
        {
            return season_loss;
        }
        public int getPlayoff_win()
        {
            return playoff_win;
        }
        public int getPlayoff_loss()
        {
            return playoff_loss;
        }
        public String getTeam()
        {
            return team;
        }
        @Override
        public String toString()
        {
            return coach_ID + " " + year + " " + first_name + " " + last_name + " " + season_win + " " + season_loss + " " + playoff_win + " " + playoff_loss 
            + " " + team;
        }

    }

    private class Team
    {
        private String team_ID;
        private String location;
        private String name;
        private String league;

        public Team(String team_ID, String location, String name, String league)
        {
            this.team_ID = team_ID;
            this.location = location;
            this.name = name;
            this.league = league;
        }
        public String getLocation()
        {
            return location;
        }
        @Override
        public String toString()
        {
            return team_ID + " " + location + " " + name + " " + league;
        }
    }
}
