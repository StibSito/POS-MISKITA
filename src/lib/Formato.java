package lib;

public class Formato {
	
	public static String estilo(double num) {
		String cadena = String.format("%.2f", num);
		String[] s = cadena.split(",");
		return estilo(Long.parseLong(s[0])) + "." + s[1];
	}

	public static String estilo(long num) {
		String cad = "";
		int x = 0;
		while (num > 0) {
			x++;
			if (num < 1000)
				cad = num % 1000 + cad;
			else
				cad = String.format("%03d", num % 1000) + cad;
			num /= 1000;
			if (num > 0)
				if (x == 2)
					cad = "1" + cad;
				else if (x == 4)
					cad = "2" + cad;
				else if (x == 6)
					cad = "3" + cad;
				else
					cad = "," + cad;
		}
		
		return cad;
	}

	
	public static String romano(int num) {
		byte unidad, decena, centena, millar;
		String uu = "", dd = "", cc = "", mm = "";
		if (num > 0 && num < 4000) {
			unidad = (byte) (num % 10);
			num /= 10;
			decena = (byte) (num % 10);
			num /= 10;
			centena = (byte) (num % 10);
			millar = (byte) (num / 10);
			switch (millar) {
			case 1:
				mm = "M";
				break;
			case 2:
				mm = "MM";
				break;
			case 3:
				mm = "MMM";
				break;
			}
			switch (centena) {
			case 1:
				cc = "C";
				break;
			case 2:
				cc = "CC";
				break;
			case 3:
				cc = "CCC";
				break;
			case 4:
				cc = "CD";
				break;
			case 5:
				cc = "D";
				break;
			case 6:
				cc = "DC";
				break;
			case 7:
				cc = "DCC";
				break;
			case 8:
				cc = "DCCC";
				break;
			case 9:
				cc = "CM";
				break;
			}
			switch (decena) {
			case 1:
				dd = "X";
				break;
			case 2:
				dd = "XX";
				break;
			case 3:
				dd = "XXX";
				break;
			case 4:
				dd = "XL";
				break;
			case 5:
				dd = "L";
				break;
			case 6:
				dd = "LX";
				break;
			case 7:
				dd = "LXX";
				break;
			case 8:
				dd = "LXXX";
				break;
			case 9:
				dd = "XC";
				break;
			}
			switch (unidad) {
			case 1:
				uu = "I";
				break;
			case 2:
				uu = "II";
				break;
			case 3:
				uu = "III";
				break;
			case 4:
				uu = "IV";
				break;
			case 5:
				uu = "V";
				break;
			case 6:
				uu = "VI";
				break;
			case 7:
				uu = "VII";
				break;
			case 8:
				uu = "VIII";
				break;
			case 9:
				uu = "IX";
				break;
			}
			return mm + cc + dd + uu;
		} else
			return "fuera de rango";
	}

	public static String texto(double num) {
		String cadena = String.format("%.2f", num);
		String[] s = cadena.split(",");
		return texto(Long.parseLong(s[0])) + " con " + s[1] + "/100";
	}

	public static String texto(long num) {
		String cad = "", c;
		byte x = 0;
		int n;
		while (num > 0) {
			x++;
			n = (int) (num % 1000);
			c = "";
			switch (x) {
			case 2:
			case 4:
			case 6:
			case 8:
				if (n > 0)
					c = " mil";
				break;
			case 3:
				if (num % 1000000 > 0)
					if (num == 1)
						c = " millón";
					else
						c = " millones";
				break;
			case 5:
				if (num % 1000000 > 0)
					if (num == 1)
						c = " billón";
					else
						c = " billones";
				break;
			case 7:
				if (num == 1)
					c = " trillón";
				else
					c = " trillones";
				break;
			}
			cad = letras(n) + c + cad;
			if (x == 1 && num % 10 == 1)
				cad += "o";
			num /= 1000;
			if (n > 0 && num > 0)
				cad = " " + cad;
		}
		return cad;
	}

	private static String letras(int num) {
		byte centena, decena, unidad;
		String cadena, cc, dd, uu;
		centena = (byte) (num / 100);
		num %= 100;
		decena = (byte) (num / 10);
		unidad = (byte) (num % 10);
		cc = "";
		switch (centena) {
		case 1:
			cc = "cien";
			if (decena != 0 || unidad != 0)
				cc += "to";
			break;
		case 2:
			cc = "doscientos";
			break;
		case 3:
			cc = "trescientos";
			break;
		case 4:
			cc = "cuatrocientos";
			break;
		case 5:
			cc = "quinientos";
			break;
		case 6:
			cc = "seiscientos";
			break;
		case 7:
			cc = "setecientos";
			break;
		case 8:
			cc = "ochocientos";
			break;
		case 9:
			cc = "novecientos";
			break;
		}
		dd = "";
		switch (decena) {
		case 1:
			switch (unidad) {
			case 0:
				dd = "diez";
				break;
			case 1:
				dd = "once";
				break;
			case 2:
				dd = "doce";
				break;
			case 3:
				dd = "trece";
				break;
			case 4:
				dd = "catorce";
				break;
			case 5:
				dd = "quince";
				break;
			default:
				dd = "dieci";
			}
			break;
		case 2:
			dd = "veint";
			if (unidad == 0)
				dd += "e";
			else
				dd += "i";
			break;
		case 3:
			dd = "treint";
			break;
		case 4:
			dd = "cuarent";
			break;
		case 5:
			dd = "cincuent";
			break;
		case 6:
			dd = "sesent";
			break;
		case 7:
			dd = "setent";
			break;
		case 8:
			dd = "ochent";
			break;
		case 9:
			dd = "novent";
			break;
		}
		if (decena > 2) {
			dd += "a";
			if (unidad > 0)
				dd += " y ";
		}
		uu = "";
		if (decena != 1 || unidad > 5)
			switch (unidad) {
			case 1:
				uu = "un";
				break;
			case 2:
				uu = "dos";
				break;
			case 3:
				uu = "tres";
				break;
			case 4:
				uu = "cuatro";
				break;
			case 5:
				uu = "cinco";
				break;
			case 6:
				uu = "seis";
				break;
			case 7:
				uu = "siete";
				break;
			case 8:
				uu = "ocho";
				break;
			case 9:
				uu = "nueve";
				break;
			}
		if (decena == 0 && unidad == 0)
			cadena = cc;
		else if (centena == 0)
			cadena = cc + dd + uu;
		else
			cadena = cc + " " + dd + uu;
		return cadena;
	}
}
