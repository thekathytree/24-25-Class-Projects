package com.svgs;
import java.util.*;

public class AchievementTracker
  {
    private HashSet<String> ach = new HashSet<String>();

   public HashSet<String> getAchievements() {
      return ach;
    }
    
    public String earnAchievement(String ac)
    { 
      if(ach.contains(ac))
      {
        return "You've already earned that!\n";
      }
      else {
      ach.add(ac);
      return "You earned " + ac + "! Good job!\n";}
    }

    public String removeAchievement(String ac)
    {
      if(!ach.contains(ac))
      {
        return "That achievement doesn't exist.\n";
      }
      else{ach.remove(ac);
      return "Removed " + ac + "\n";}
    }
    public String listAchievements()
    {
      String ret = "";
      for (String a : ach)
        {
          ret += a + " ";
        }
      return ret + "\n";
    }
 
    public boolean getAc(String ac)
    {
      for(String a : ach)
        {
          if(a.equals(ac))
          {
            return true;
          }
        }
        return false;
    }
  }