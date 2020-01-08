package com.github.mxsm.raft.core;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * description:
 *
 * @author mxsm
 * @Date 2020/1/8 22:55
 */
public class RaftPeer {

    /**
     * RaftPeer的IP地址(可能是ip:port或者ip)
     */
    public String ip;

    /**
     * 投票的RaftPeer的IP地址
     */
    public String voteFor;

    /**
     * 任期
     */
    public AtomicLong term = new AtomicLong(0);

    /**
     * leader任期剩余时间
     */
    public volatile long leaderDueMs = RandomUtils.nextLong(0, TimeUnit.SECONDS.toMillis(5));

    /**
     * 心跳发送剩余时间
     */
    public volatile long heartbeatDueMs = RandomUtils.nextLong(150, TimeUnit.MILLISECONDS.toMillis(150));


    public void resetHeartbeatDueMs(){
        heartbeatDueMs = TimeUnit.SECONDS.toMillis(5);
    }

    public void restLeaderDueMs(){
        leaderDueMs = TimeUnit.SECONDS.toMillis(15) + RandomUtils.nextLong(0, TimeUnit.SECONDS.toMillis(5));
    }

    public enum  State {

        LEADER,

        //初始状态
        FOLLOWER,

        CANDIDATE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof RaftPeer)) {
            return false;
        }

        RaftPeer raftPeer = (RaftPeer) o;

        return new EqualsBuilder()
            .append(ip, raftPeer.ip)
            .append(voteFor, raftPeer.voteFor)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(ip)
            .append(voteFor)
            .toHashCode();
    }
}
