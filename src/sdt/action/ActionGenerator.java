package sdt.action;

public class ActionGenerator {

	public static Action parseAction(String serialNumber, String description) {
		switch (serialNumber) {
		case "a1":
			return new Action01(description);
		case "a2":
			return new Action02(description);
		case "a5":
			return new Action05(description);
		case "a6":
			return new Action06(description);
		case "a7":
			return new Action07(description);
		case "a8":
			return new Action08(description);
		case "a9":
			return new Action09(description);
		case "a12":
			return new Action12(description);
		case "a13":
			return new Action13(description);
		case "a14":
			return new Action14(description);
		case "a15":
			return new Action15(description);
		case "a23":
			return new Action23(description);
		case "a24":
			return new Action24(description);
		case "a25":
			return new Action25(description);
		case "a26":
			return new Action26(description);
		case "a27":
			return new Action27(description);
		case "a28":
			return new Action28(description);
		case "a29":
			return new Action29(description);
		case "a30":
			return new Action30(description);
		case "a31":
			return new Action31(description);
		case "a32":
			return new Action32(description);
		case "a33":
			return new Action33(description);
		case "a40":
			return new Action40(description);
		case "a41":
			return new Action41(description);

		default:
		}
		return new BaseAction(description);
	}
}
