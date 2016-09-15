package shared.commands;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rpc extends Command implements Serializable {
    public String command;
    public Object[] params;
    public Object response;
}
