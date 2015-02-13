package deema;


import deema.yokta.auth.core.OktaPasscodeGenerator;

public class Main {

	static final String ACCOUNT = "cont_andreev@esri.com";
	static final String SECRET = "KFWXIQLGNJLTQMCX";

    public static void main(String[] args) {
		String code = OktaPasscodeGenerator.generate(SECRET);
		System.out.println(code);
    }
}
