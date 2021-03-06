DROP VIEW news_view;

CREATE VIEW news_view AS SELECT
	reports.reports_id,
    reports.reports_name,
    reports.reports_type,
    reports.reports_content1,
    reports.reports_eyecatch,
    reports.reports_heading1,
    reports.register_time, 
    reports.register_person, 
    reports.delete_time, 
    reports.delete_person, 
    reports.reports_show, 
    makers.makers_name AS makers_name,
    makers.makers_homepage AS makers_homepage,
    makers.delete_time AS m_delete_time,
    makers.delete_person AS m_delete_person,
    makers_show
FROM reports
LEFT JOIN makers ON reports.makers_id = makers.makers_id
WHERE reports.reports_type = 3 
AND reports.delete_time IS NULL AND reports.delete_person IS NULL 
ORDER BY register_time DESC;
