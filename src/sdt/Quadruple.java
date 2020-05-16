package sdt;

public class Quadruple {

	private Object op;
	private Object arg1;
	private Object arg2;
	private Object result;

	/**
	 * @param op
	 * @param arg1
	 * @param arg2
	 * @param result
	 */
	public Quadruple(Object op, Object arg1, Object arg2, Object result) {
		super();
		this.op = op;
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.result = result;
	}

	/**
	 * @return the op
	 */
	public Object getOp() {
		return op;
	}

	/**
	 * @return the arg1
	 */
	public Object getArg1() {
		return arg1;
	}

	/**
	 * @return the arg2
	 */
	public Object getArg2() {
		return arg2;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param op the op to set
	 */
	public void setOp(Object op) {
		this.op = op;
	}

	/**
	 * @param arg1 the arg1 to set
	 */
	public void setArg1(Object arg1) {
		this.arg1 = arg1;
	}

	/**
	 * @param arg2 the arg2 to set
	 */
	public void setArg2(Object arg2) {
		this.arg2 = arg2;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arg1 == null) ? 0 : arg1.hashCode());
		result = prime * result + ((arg2 == null) ? 0 : arg2.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quadruple other = (Quadruple) obj;
		if (arg1 == null) {
			if (other.arg1 != null)
				return false;
		} else if (!arg1.equals(other.arg1))
			return false;
		if (arg2 == null) {
			if (other.arg2 != null)
				return false;
		} else if (!arg2.equals(other.arg2))
			return false;
		if (op == null) {
			if (other.op != null)
				return false;
		} else if (!op.equals(other.op))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + op + ", " + arg1 + ", " + arg2 + ", " + result + ")";
	}
	
	

}
