select * from states;
select * from cities;
select * from addresses;
select * from venues;
select * from organizations_venues;
select * from organizations;

delete from states;
delete from cities;
delete from addresses;
delete from organizations;
delete from venues;
delete from organizations_venues;

call clean;

-- --------------------------------------------------

select * from organizations where name like '%amics%';

select *
from
	organizations o
	inner join organizations_venues ov on (
		ov.organization_id = o.id
    )
    inner join venues v on (
		ov.venue_id = v.id
    )
    inner join addresses a on (
		v.address_id = a.id
    )
    inner join states s on (
		a.state_id = s.id
    )
    inner join cities c on (
		a.city_id = c.id
    )
 where
	1=1
	-- o.name like '%priego%'
    -- s.name like '%girona%'
    -- o.id=1493
    -- and ov.season='2015-2016'
    -- and ov.season='2014-2015'
    -- and a.street like '%azalea%'
    ;

-- --------------------------------------------------

select address_id, a.street, count(*) as cc
from
	venues v
    inner join addresses a on (
		v.address_id = a.id
    )
group by
	address_id
order by cc desc