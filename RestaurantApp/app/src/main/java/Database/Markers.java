package Database;

public class Markers
{
    private long id;
    private long latitude;
    private long longitude;

    public long getId()
    {
        return (id);
    }

    public void setId(final long i)
    {
        id = i;
    }

    public long getLatitude()
    {
        return (latitude);
    }

    public long getLongitude()
    {
        return (longitude);
    }

    public void setLatitude(final long l)
    {
        latitude = l;
    }

    public void setLongitude(final long l)
    {
        longitude = l;
    }
}