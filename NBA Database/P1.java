/* Name: Diya Jain U81673988 */
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
        else if (cmd.getCommand().equals("add_coach")) { /* Check if the command passed in is "add_coach" */
            String[] arguments = cmd.getParameters(); /* Assign the rguments passed in to a string array */
            Coach c = new Coach(arguments[0], Integer.parseInt(arguments[1]), 0 , arguments[2],
            arguments[3], Integer.parseInt(arguments[4]), Integer.parseInt(arguments[5]), Integer.parseInt(arguments[6]), Integer.parseInt(arguments[7])
            ,arguments[8]); /* Create a new Coach object with the given arguments */
            coaches.add(c);   /* Add the coach object to the database */
	    }
        else if (cmd.getCommand().equals("add_team")) { /* Check if the command passed in is "add_team" */
        	String[] arguments = cmd.getParameters(); /* Assign the arguments passed in to a string array */
            Team t = new Team (arguments[0],arguments[1], arguments[2], arguments[3]); /* Create a new Team object with the given arguments */
            teams.add(t);  /* Add the team object to the database */
		}
        else if (cmd.getCommand().equals("print_coaches")) {
            for(int i = 0; i<coaches.size();i++) /* Iterate through the list of coaches */
            {
                System.out.println(coaches.get(i)); /* Print each coach in the list */
            }
	   	}
        else if (cmd.getCommand().equals("print_teams")) {
            for(int i = 0; i<teams.size();i++) /* Iterate through the list of teams */
            {
                System.out.println(teams.get(i)); /* Print each coach in the list */
            }
		} 
        else if (cmd.getCommand().equals("coaches_by_name")) {
            String arguments[] = cmd.getParameters();
            for(int i = 0; i<coaches.size();i++) /* Loop through the coaches in the database */
            {
                if (coaches.get(i).getLast_name().trim().equalsIgnoreCase(arguments[0].replace("+", ""))) 
                /* Check if the coach's last name (ignoring case) matches the argument passed in */
                {
                    System.out.println(coaches.get(i)); /* Print the coach's information */
                }
            }
		} 
        else if (cmd.getCommand().equals("teams_by_city")) {
            String arguments[] = cmd.getParameters();
            for(int i = 0; i<teams.size();i++) /* Loop through the list of teams */
            {
                if (teams.get(i).getLocation().trim().equals(arguments[0]))
                /* Check if the city of the current team matches the city passed in as a parameter */
                {
                    System.out.println(teams.get(i)); /* Print the team if the city matches */
                }
            }
		}
        else if (cmd.getCommand().equals("load_coaches")) 
        {
            String arguments[] = cmd.getParameters();
            Scanner infile; /* Initialize a scanner object to read from a file */
            try 
            {
                infile = new Scanner(new File(arguments[0])); 
                /* Attempt to create a new scanner object to read the specified file */

                String line;
                String[] values;

                if (infile.hasNextLine()) /* Check if the file has a next line */
                    line = infile.nextLine(); /* If it does, assign the next line to the line variable */
                
                while (infile.hasNextLine())
                {
                    line = infile.nextLine();
                    values = line.split(","); 
                    /* Split the values of the line by the comma delimiter and assign them to the values array */
                    if (values.length == 10) 
                    /* Check if the values array has a length of 10 */
                    {
                        Coach c = new Coach(values[0], Integer.parseInt(values[1]), 0, values[3], values[4],
                        Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]), Integer.parseInt(values[8])
                        ,(values[9]));
                        coaches.add(c); /* Add the coach object to the coaches array */
                    }

                }
                infile.close();
            }
            catch(FileNotFoundException e)
            /* Catch a FileNotFoundException in case the specified file does not exist */
            {
                System.out.println(arguments[0] + " file does not exist!");
            }
        }
        else if (cmd.getCommand().equals("load_teams")) 
        {
         
            String arguments[] = cmd.getParameters();
            Scanner infile; /* Initialize a scanner object to read from a file */
            try
            {
                infile = new Scanner(new File(arguments[0]));

                String line;
                String[] values;

                if (infile.hasNextLine()) /* Check if the file has a next line */
                    line = infile.nextLine(); /* If it does, assign the next line to the line variable */
                
                while (infile.hasNextLine())
                {
                    line = infile.nextLine();
                    values = line.split(",");
                    if (values.length == 4) /* Check if the values array has a length of 4 */
                    {
                        Team t = new Team(values[0], values[1], values[2], values[3]);
                        teams.add(t); /* Add the team object to the coaches array */
                    }
                }
                infile.close();
            }
            catch(FileNotFoundException e)
            /* Catch a FileNotFoundException in case the specified file does not exist */
            {
                System.out.println(arguments[0] + "file does not exist!");
            }
        }
            else if (cmd.getCommand().equals("best_coach")) 
            {
            String arguments[] = cmd.getParameters();
            int bestCoachNetWins = 0; /* Initialize a variable to keep track of the best coach's net wins */
            int netWins; /* store the current coach's net wins */
            Coach c;
            
            for (int i = 0; i<coaches.size(); i++)
            {
                c = coaches.get(i);
    
                if (c.getYear() == Integer.parseInt(arguments[0]))
                /* Check if the coach's year matches the given parameter */
                {
                    netWins = (c.getSeason_win() - c.getSeason_loss() + c.getPlayoff_win() - c.getPlayoff_loss());
                    /* Calculate the coach's net wins */
    
                    if(netWins > bestCoachNetWins) /* Check if the coach's net wins are higher than the current highest */
                    {
                        bestCoachNetWins = netWins;
                    }
                }
            }
            for (int i =0; i<coaches.size(); i++)
            {
                c = coaches.get(i);
    
                if (c.getYear() == Integer.parseInt(arguments[0]))
                {
                    netWins = (c.getSeason_win() - c.getSeason_loss() + c.getPlayoff_win() - c.getPlayoff_loss());
                    if(netWins == bestCoachNetWins)
                    {
                        System.out.println(c.getFirst_name() + " " + c.getLast_name()); /* Print the coach's name */
                    }
                }
            }
        }    
        else if (cmd.getCommand().equals("search_coaches")) 
        {
            String[] arguments = cmd.getParameters(); /* Assign the parameters passed in to a string array */
            String[] tokens = arguments[0].split("="); /* Split the argument by "=" */
            String field = tokens[0];
            String value = tokens[1];
            
            for (int i = 0; i<coaches.size(); i++)
            {
                Coach c = coaches.get(i);

                if (field.equalsIgnoreCase("coachid") && value.equalsIgnoreCase(c.getCoach_ID()) ||
                    field.equalsIgnoreCase("year") && Integer.parseInt(value) == c.getYear() ||
                    field.equalsIgnoreCase("yr_order") && Integer.parseInt(value) == c.getYr_order() ||
                    field.equalsIgnoreCase("firstname") && value.equalsIgnoreCase(c.getFirst_name()) ||
                    field.equalsIgnoreCase("lastname") && value.equalsIgnoreCase(c.getLast_name()) ||
                    field.equalsIgnoreCase("season_win") && Integer.parseInt(value) == c.getSeason_win() ||
                    field.equalsIgnoreCase("season_loss") && Integer.parseInt(value) == c.getSeason_loss() ||
                    field.equalsIgnoreCase("playoff_win") && Integer.parseInt(value) == c.getPlayoff_win() ||
                    field.equalsIgnoreCase("playoff_loss") && Integer.parseInt(value) == c.getPlayoff_loss() ||
                    field.equalsIgnoreCase("team") && value.equalsIgnoreCase(c.getTeam())) 
                {
             
                    System.out.println(coaches.get(i));
                }
            }
		}
        else if (cmd.getCommand().equals("exit")) 
        {
			System.out.println("Leaving the database, goodbye!");
			break;
		} 
        else if (cmd.getCommand().equals("")) 
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
    
    private class Coach /* Coach class representing a Coach object */
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
        int season_win, int season_loss, int playoff_win, int playoff_loss, String team) /* Coach class constructor */
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
        /* Getters for Coach class properties */
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

    private class Team /* Team class representing a Team object */
    {
        private String team_ID;
        private String location;
        private String name;
        private String league;

        public Team(String team_ID, String location, String name, String league) /* Team class constructor */
        {
            this.team_ID = team_ID;
            this.location = location;
            this.name = name;
            this.league = league;
        }
        public String getname() {
            return name;
        }
        public String getleague() {
            return league;
        }
        public String getLocation() /* Getter for Team class's location property */
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