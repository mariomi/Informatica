# This file is auto-generated by the Perl DateTime Suite time zone
# code generator (0.08) This code generator comes with the
# DateTime::TimeZone module distribution in the tools/ directory

#
# Generated from /tmp/h6QqPsv6Ap/asia.  Olson data version 2020e
#
# Do not edit this file directly.
#
package DateTime::TimeZone::Asia::Manila;

use strict;
use warnings;
use namespace::autoclean;

our $VERSION = '2.46';

use Class::Singleton 1.03;
use DateTime::TimeZone;
use DateTime::TimeZone::OlsonDB;

@DateTime::TimeZone::Asia::Manila::ISA = ( 'Class::Singleton', 'DateTime::TimeZone' );

my $spans =
[
    [
DateTime::TimeZone::NEG_INFINITY, #    utc_start
58191062160, #      utc_end 1844-12-31 15:56:00 (Tue)
DateTime::TimeZone::NEG_INFINITY, #  local_start
58191004800, #    local_end 1844-12-31 00:00:00 (Tue)
-57360,
0,
'LMT',
    ],
    [
58191062160, #    utc_start 1844-12-31 15:56:00 (Tue)
59906361360, #      utc_end 1899-05-10 15:56:00 (Wed)
58191091200, #  local_start 1845-01-01 00:00:00 (Wed)
59906390400, #    local_end 1899-05-11 00:00:00 (Thu)
29040,
0,
'LMT',
    ],
    [
59906361360, #    utc_start 1899-05-10 15:56:00 (Wed)
61089004800, #      utc_end 1936-10-31 16:00:00 (Sat)
59906390160, #  local_start 1899-05-10 23:56:00 (Wed)
61089033600, #    local_end 1936-11-01 00:00:00 (Sun)
28800,
0,
'PST',
    ],
    [
61089004800, #    utc_start 1936-10-31 16:00:00 (Sat)
61096950000, #      utc_end 1937-01-31 15:00:00 (Sun)
61089037200, #  local_start 1936-11-01 01:00:00 (Sun)
61096982400, #    local_end 1937-02-01 00:00:00 (Mon)
32400,
1,
'PDT',
    ],
    [
61096950000, #    utc_start 1937-01-31 15:00:00 (Sun)
61262409600, #      utc_end 1942-04-30 16:00:00 (Thu)
61096978800, #  local_start 1937-01-31 23:00:00 (Sun)
61262438400, #    local_end 1942-05-01 00:00:00 (Fri)
28800,
0,
'PST',
    ],
    [
61262409600, #    utc_start 1942-04-30 16:00:00 (Thu)
61341462000, #      utc_end 1944-10-31 15:00:00 (Tue)
61262442000, #  local_start 1942-05-01 01:00:00 (Fri)
61341494400, #    local_end 1944-11-01 00:00:00 (Wed)
32400,
0,
'JST',
    ],
    [
61341462000, #    utc_start 1944-10-31 15:00:00 (Tue)
61639459200, #      utc_end 1954-04-11 16:00:00 (Sun)
61341490800, #  local_start 1944-10-31 23:00:00 (Tue)
61639488000, #    local_end 1954-04-12 00:00:00 (Mon)
28800,
0,
'PST',
    ],
    [
61639459200, #    utc_start 1954-04-11 16:00:00 (Sun)
61646367600, #      utc_end 1954-06-30 15:00:00 (Wed)
61639491600, #  local_start 1954-04-12 01:00:00 (Mon)
61646400000, #    local_end 1954-07-01 00:00:00 (Thu)
32400,
1,
'PDT',
    ],
    [
61646367600, #    utc_start 1954-06-30 15:00:00 (Wed)
62395027200, #      utc_end 1978-03-21 16:00:00 (Tue)
61646396400, #  local_start 1954-06-30 23:00:00 (Wed)
62395056000, #    local_end 1978-03-22 00:00:00 (Wed)
28800,
0,
'PST',
    ],
    [
62395027200, #    utc_start 1978-03-21 16:00:00 (Tue)
62410834800, #      utc_end 1978-09-20 15:00:00 (Wed)
62395059600, #  local_start 1978-03-22 01:00:00 (Wed)
62410867200, #    local_end 1978-09-21 00:00:00 (Thu)
32400,
1,
'PDT',
    ],
    [
62410834800, #    utc_start 1978-09-20 15:00:00 (Wed)
DateTime::TimeZone::INFINITY, #      utc_end
62410863600, #  local_start 1978-09-20 23:00:00 (Wed)
DateTime::TimeZone::INFINITY, #    local_end
28800,
0,
'PST',
    ],
];

sub olson_version {'2020e'}

sub has_dst_changes {3}

sub _max_year {2030}

sub _new_instance {
    return shift->_init( @_, spans => $spans );
}



1;

