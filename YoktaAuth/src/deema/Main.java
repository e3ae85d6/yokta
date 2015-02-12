package deema;


import deema.yokta.auth.core.OktaPasscodeGenerator;

public class Main {

    public static void main(String[] args) {

		String code = OktaPasscodeGenerator.generate("JBFGS3BSJ5VUCNDQ");

		System.out.println(code);
    }
}
