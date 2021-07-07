package com.ashawsolutions.waterspile;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {
    private final HashMap<String, Long> _cooldowns = new HashMap<>();

    public final static long DEFAULT_COOLDOWN = 10;

    public void setCooldown(String playerId){
        _cooldowns.put(playerId, System.currentTimeMillis());
    }

    public long getCooldown(String playerId){
        int output = 0;

        try{
            if(_cooldowns.containsKey(playerId)){
                long secondsLeft = ((_cooldowns.get(playerId)/1000)+ DEFAULT_COOLDOWN) - (System.currentTimeMillis()/1000);
                if(secondsLeft > 0){
                    return secondsLeft;
                }
            }
        }
        catch (Exception ex){

        }

        return output;
    }
}
