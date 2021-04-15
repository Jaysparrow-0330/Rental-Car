/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.dtos;

/**
 *
 * @author jack3
 */
public class ErrorDTO {

    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String line5;
    private String line6;
    private String line7;

    public ErrorDTO() {
    }

    public ErrorDTO(String line1, String line2, String line3, String line4, String line5, String line6, String line7) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.line5 = line5;
        this.line6 = line6;
        this.line7 = line7;
    }

  

    public String getLine6() {
        return line6;
    }

    public void setLine6(String line6) {
        this.line6 = line6;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public String getLine5() {
        return line5;
    }

    public void setLine5(String line5) {
        this.line5 = line5;
    }

    public String getLine7() {
        return line7;
    }

    public void setLine7(String line7) {
        this.line7 = line7;
    }

}
