package edu.allegheny.releaseplanner;
import com.beust.jcommander.*;

import java.util.ArrayList;
import java.util.List;

public class Parameters {

    @Parameter(names = "-file",description = "CSV file of inputs") 
    public String fileName;

    @Parameter(names = "-C",description = "Total Cost",required=true) 
    public Integer w;

    @Parameter(names = "-rq",description = "Requirements",variableArity = true)
    public List<String> req = new ArrayList<String>();

}
