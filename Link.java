public class Link extends PathyObject
{
	private PathyPlace a;
	private PathyPlace b;
	private int weight;
	private LinkDir direction;

	private void checkNotNull(PathyPlace _a, PathyPlace _b)
	{
		if (_a == null && _b == null)
		{
			throw new RuntimeException("Endpoints A and B must be non-null");
		}
		if (_a == null)
		{
			throw new RuntimeException("Endpoint A must be non-null");
		}
		if (_b == null)
		{
			throw new RuntimeException("Endpoint B must be non-null");
		}
		if (_a == _b || _a.equals(_b))
		{
			throw new RuntimeException("Endpoints must not be identical");
		}
	}

	public Link(String _id, PathyPlace _a, PathyPlace _b)
	{
		super(_id, PathyType.LINK);
		checkNotNull(_a,_b);
		a = _a;
		b = _b;
		weight = 0;
		direction = LinkDir.TWOWAY;
		_a.addConnection(this);
		_b.addConnection(this);
	}

	public Link(String _id, PathyPlace _a, PathyPlace _b, int _w)
	{
		super(_id, PathyType.LINK);
		checkNotNull(_a,_b);
		a = _a;
		b = _b;
		weight = _w;
		direction = LinkDir.TWOWAY;
		_a.addConnection(this);
		_b.addConnection(this);
	}

	public Link(String _id, PathyPlace _a, PathyPlace _b, int _w, LinkDir _d)
	{
		super(_id, PathyType.LINK);
		checkNotNull(_a,_b);
		a = _a;
		b = _b;
		weight = _w;
		direction = _d;
		_a.addConnection(this);
		_b.addConnection(this);
	}

	public PathyPlace getA()
	{
		return a;
	}

	public PathyPlace getB()
	{
		return b;
	}

	public void delink()
	{
		a.removeConnection(this);
		b.removeConnection(this);
	}
	
	public boolean isEndpoint(PathyPlace end)
	{
		return end == a || end == b;
	}

	public void setWeight(int w)
	{
		weight = w;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setDirection(LinkDir d)
	{
		direction = d;
	}

	public LinkDir getDirection()
	{
		return direction;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + weight;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//most of equals is a bit redundant as if (this == obj) returns false then super.equals(obj) will likely do so too as ids won't be the same
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		//altered auto-gen code as equals should auto-fail if the endpoint of either object is null
		if (other.a == null || a == null) {
			return false;
		} else if (!a.equals(other.a))
			return false;
		if (other.b == null || b == null) {
			return false;
		} else if (!b.equals(other.b))
			return false;
		if (direction != other.direction)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	//identical is the same as equals but doesn't check the id, so this is used to compare if two different links do exactly the same things.
	public boolean identical(Object obj)
	{
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (type != other.type)
			return false;
		if (other.a == null || a == null) {
			return false;
		} else if (!a.equals(other.a))
			return false;
		if (other.b == null || b == null) {
			return false;
		} else if (!b.equals(other.b))
			return false;
		if (direction != other.direction)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
}
