CREATE DEFINER=`root`@`localhost` PROCEDURE `clean`()
BEGIN
	delete from organizations_venues;
    delete from organization_aliases;

    delete from organizations;
    delete from venues;
    delete from addresses;
    delete from cities;
    delete from states;

END