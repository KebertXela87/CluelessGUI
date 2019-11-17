package edu.jhu.teamundecided.clueless.player;

import edu.jhu.teamundecided.clueless.gameboard.Room;
import edu.jhu.teamundecided.clueless.server.ClientHandler;

public class Player
{
    private String _characterName = "";
    private Room _currentLocation;
    private String _userName;
//    private ServerWorker _serverWorker;
    private boolean _isActive;
    private Hand _playerHand;


    public Player()
    {
    }

    public Hand getPlayerHand()
    {
        return _playerHand;
    }

    public void setUserName(String name)
    {
        _userName = name;
    }

    public String getUserName()
    {
        return _userName;
    }

    public String getCharacterName()
    {
        return _characterName;
    }

    public void setCharacterName(String _characterName)
    {
        this._characterName = _characterName;
    }

    public Room getLocation() { return _currentLocation; }
}