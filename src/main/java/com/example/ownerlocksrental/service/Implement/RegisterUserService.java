package com.example.ownerlocksrental.service.Implement;
import com.example.ownerlocksrental.entities.Owner;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.X509Identity;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.Properties;
import java.util.Set;
@Service
public class RegisterUserService {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    public  void registerUser(Owner owner) throws Exception {
        URL url = new URL("http://localhost:5984");
        // Create a CA client for interacting with the CA.
        Properties props = new Properties();
        props.put("pemFile",
                "/home/medwajdisassi/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem");
        props.put("allowAllHostNames", "true");
        HFCAClient caClient = HFCAClient.createNewInstance("https://localhost:7054", props);
        CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
        caClient.setCryptoSuite(cryptoSuite);

        // Create a wallet for managing identities
        //Wallet wallet = Wallet.createFileSystemWallet(Paths.get("wallet"));
        Wallet wallet = Wallets.newCouchDBWallet(url, "onlinewallet");

        // Check to see if we've already enrolled the user.
        //Identity userExists = wallet.get("appUser");
        if (wallet.get(owner.getFirstName()) != null) {
            System.out.println("An identity for the user \"appUser\" already exists in the wallet");
            return;
        }

        X509Identity adminIdentity = (X509Identity) wallet.get("admin");
        if (adminIdentity == null) {
            System.out.println("\"admin\" needs to be enrolled and added to the wallet first");
            return;
        }


        User admin = new User() {


            @Override
            public String getName() {
                return owner.getFirstName();
            }

            @Override
            public Set<String> getRoles() {
                return null;
            }

            @Override
            public String getAccount() {
                return null;
            }

            @Override
            public String getAffiliation() {
                return "org1.department1";
            }

            @Override
            public Enrollment getEnrollment() {
                return new Enrollment() {
                    @Override
                    public PrivateKey getKey() {
                        return adminIdentity.getPrivateKey();
                    }

                    @Override
                    public String getCert() {
                        return Identities.toPemString(adminIdentity.getCertificate());
                    }
                };
            }

            @Override
            public String getMspId() {
                return "Org1MSP";
            }
        };

        // Register the user, enroll the user, and import the new identity into the wallet.
        RegistrationRequest registrationRequest = new RegistrationRequest("appUser");
        registrationRequest.setAffiliation("org1.department1");


        registrationRequest.setEnrollmentID(owner.getFirstName());
        String enrollmentSecret = caClient.register(registrationRequest, admin);
        Enrollment enrollment = caClient.enroll(owner.getFirstName(), enrollmentSecret);
        //Identity user = Identities.newX509Identity("Org1MSP",  enrollmentSecret);
        Identity user = Identities.newX509Identity("Org1MSP", enrollment);

        wallet.put(owner.getFirstName()+" "+owner.getLastName(), user);

        System.out.println("Successfully enrolled user \"appUser\" and imported it into the wallet");

    }


}
