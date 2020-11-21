package com.stasiuk.program;

public class FacadePC {
    private OnPC onPC = new OnPC();
    private OffPC offPC = new OffPC();
    private StartOS startOS = new StartOS();
    private CompletionOS сompletionOS = new CompletionOS();
    private RunningTheProgram runningTheProgram = new RunningTheProgram();

    public FacadePC(){

    }

    public String StartProgram(ProgramOnPC _programOnPC){
        switch (_programOnPC){
            case GoogleChrome :
            case uTorrent :{
                return onPC.On()+startOS.Start(OS.Windows)+runningTheProgram.Start(_programOnPC);
            }
            case VisualStudioCode:
                return onPC.On()+startOS.Start(OS.Linux)+runningTheProgram.Start(_programOnPC);
            default:
                return onPC.On();
        }
    }
    public String StartProgramAndEnd(ProgramOnPC _programOnPC){
        switch (_programOnPC){
            case GoogleChrome :
            case uTorrent :{
                return onPC.On()+startOS.Start(OS.Windows)+runningTheProgram.Start(_programOnPC)+сompletionOS.Сompletion()+offPC.Off();
            }
            case VisualStudioCode:
                return onPC.On()+startOS.Start(OS.Linux)+runningTheProgram.Start(_programOnPC)+сompletionOS.Сompletion()+offPC.Off();
            default:
                return onPC.On()+offPC.Off();
        }
    }

}
