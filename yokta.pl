#!/usr/bin/perl -w

use strict;
use MIME::Base32 qw( RFC );
use Digest::SHA qw( hmac_sha1 );

use constant OKTA_SECRET => "KFWXIQLGNJLTQMCX";

##################################################
# RFC 6238 TOTP Algorithm
##################################################

sub yokta_code {
    use constant TIME_STEP    => 30;
    use constant PASSCODE_LEN => 6;
    use constant MODULO       => 10 ** PASSCODE_LEN;

    my $secret = $_[0];
    my $interval = int(time() / TIME_STEP);

    my $hash = hmac_sha1(
        pack("Q>", $interval),
        MIME::Base32::decode($secret));

    my @hash_bytes = unpack("(C)*", $hash);

    my $offset = $hash_bytes[-1] & 0xf;
    my @int_data = splice(@hash_bytes, $offset, 4);

    my $int_code = unpack("L>", pack("C4", @int_data));
    $int_code = (0x7fffffff & $int_code) % MODULO;

    return sprintf("%0".PASSCODE_LEN."d", $int_code);
}

my $code = yokta_code(OKTA_SECRET);

print "$code\n";

