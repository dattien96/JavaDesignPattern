package com.company.iterator.demo2;

/**
 * Lưu ý rằng ta k hề có fun return list data
 */
public interface ChannelCollection {
    public void addChannel(Channel c);

    public void removeChannel(Channel c);

    public ChannelIterator iterator(ChannelTypeEnum type);
}
