package bt.tracker.http;

import bt.peer.IPeerRegistry;
import bt.protocol.crypto.EncryptionPolicy;
import bt.runtime.Config;
import bt.service.IdentityService;
import bt.tracker.Tracker;
import bt.tracker.TrackerFactory;
import com.google.inject.Inject;

import java.net.InetAddress;

/**
 * Creates HTTP tracker clients.
 *
 * @since 1.0
 */
public class HttpTrackerFactory implements TrackerFactory {

    private IdentityService idService;
    private IPeerRegistry peerRegistry;
    private EncryptionPolicy encryptionPolicy;
    private InetAddress localAddress;

    @Inject
    public HttpTrackerFactory(IdentityService idService, IPeerRegistry peerRegistry, Config config) {
        this.idService = idService;
        this.peerRegistry = peerRegistry;
        this.encryptionPolicy = config.getEncryptionPolicy();
        this.localAddress = config.getAcceptorAddress();
    }

    @Override
    public Tracker getTracker(String trackerUrl) {
        return new HttpTracker(trackerUrl, idService, peerRegistry, encryptionPolicy, localAddress);
    }
}
