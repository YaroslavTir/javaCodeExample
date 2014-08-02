package ru.fls.privateoffice.util.dto;

/**
 * User: NKarataeva
 * Date: 10.07.12
 * Time: 15:00
 */
public class ProgramElementDTO {
    private String programType;

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public ProgramElementDTO() {
    }

    public ProgramElementDTO(String programType) {
        this.programType = programType;
    }
}
