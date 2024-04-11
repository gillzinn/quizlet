package vinnsla;

public class Spurning {
    private String spurning;//spurningin sjálf
    private String svar;// svarið
    private String[] rongsvor = new String[4];// fylki sem inniheldur alla svarmöguleikana


    /**
     * Spurning smiður
     *
     * @param sp spurningin
     * @param sv svarið
     * @param a  rangur svarmöguleiki 1
     * @param b  rangur svarmöguleiki 2
     * @param c  rangur svarmöguleiki 3
     */
    public Spurning(String sp, String sv, String a, String b, String c) {
        spurning = sp;
        svar = sv;
        rongsvor[0] = sv;
        rongsvor[1] = a;
        rongsvor[2] = b;
        rongsvor[3] = c;
    }

    /**
     * fall sem spurningunni
     *
     * @return spurningin
     */
    public String getSpurning() {
        return spurning;
    }

    /**
     * fall sem skilar svarinu á spurningunni
     *
     * @return svar
     */
    public String getSvar() {
        return svar;
    }

    /**
     * fall sem skilar fylkinu með alla svarmöguleikana
     *
     * @return skilar svarmöguleikunum
     */
    public String[] getRongsvor() {
        return rongsvor;
    }

}

