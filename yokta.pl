#!/usr/bin/perl -w

use MIME::Base32 qw( RFC );
use Digest::SHA qw( hmac_sha1 );

my $okta_secret = "KFWXIQLGNJLTQMCX";

##################################################

sub yokta_code {
    my $time_step = 30;
    my $passcode_length = 6;
    my $modulo = 10 ** $passcode_length;

    my $secret = $_[0];
    my $interval = int(time() / $time_step);

    my $hash = hmac_sha1(
        pack("Q>", $interval),
        MIME::Base32::decode($secret));

    my @hash_bytes = unpack("(C)*", $hash);

    #print "hash: ";
    #printf("%#02x ", $_) for @hash_bytes;
    #print "\n";

    my $offset = $hash_bytes[-1] & 0xf;
    my @int_data = splice(@hash_bytes, $offset, 4);

    #print "int bytes: ";
    #printf("%#02x ", $_) for @int_data;
    #print "\n";

    my $int_code = unpack("L>", pack("C4", @int_data));
    $int_code = (0x7fffffff & $int_code) % $modulo;

    return sprintf(sprintf("%%0%dd", $passcode_length), $int_code);
}

my $code = yokta_code($okta_secret);

print "$code\n";

