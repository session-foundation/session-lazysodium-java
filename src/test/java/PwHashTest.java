/*
 * Copyright (c) Terl Tech Ltd • 03/05/18 11:37 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import com.goterl.lazycode.lazysodium.exceptions.SodiumException;
import com.goterl.lazycode.lazysodium.interfaces.PwHash;
import com.goterl.lazycode.lazysodium.interfaces.Random;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PwHashTest extends BaseTest {


    @Test
    public void strMin() {
        byte[] outputHash = new byte[PwHash.STR_BYTES];

        boolean success = pwHash.cryptoPwHashStr(
                outputHash,
                PASSWORD_BYTES,
                PASSWORD_BYTES_LEN,
                PwHash.ARGON2ID_OPSLIMIT_MIN,
                PwHash.ARGON2ID_MEMLIMIT_MIN
        );

        if (!success) {
            fail("cryptoPwHashStrMin did not succeed.");
        }

        byte[] cleanHash = lazySodium.removeNulls(outputHash);
        boolean isCorrect = pwHash.cryptoPwHashStrVerify(
                cleanHash,
                PASSWORD_BYTES,
                PASSWORD_BYTES_LEN
        );

        assertTrue("Minimum hashing failed.", isCorrect);
    }


    // We don't test for this as it's pretty demanding and
    // will fail on most machines
    public void cryptoPwHashStrTestSensitive() { }


    @Test
    public void strLazy() throws SodiumException {
        String hashed = pwHashLazy.cryptoPwHashStr(
                PASSWORD,
                PwHash.ARGON2ID_OPSLIMIT_MIN,
                PwHash.ARGON2ID_MEMLIMIT_MIN
        );

        assertNotNull("cryptoPwHashStrLazy failed.", hashed);
    }

    @Test
    public void needsRehash() throws SodiumException {
        String hashed = pwHashLazy.cryptoPwHashStr(
                PASSWORD,
                PwHash.ARGON2ID_OPSLIMIT_MIN,
                PwHash.ARGON2ID_MEMLIMIT_MIN
        );

        assertNotNull("cryptoPwHashStrLazy failed.", hashed);
    }


}
