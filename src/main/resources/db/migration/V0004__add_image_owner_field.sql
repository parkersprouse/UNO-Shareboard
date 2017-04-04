ALTER TABLE images ADD COLUMN owner integer NOT NULL REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE ad_image_xref DROP COLUMN ad_id;
ALTER TABLE ad_image_xref DROP COLUMN image_id;
ALTER TABLE ad_image_xref ADD COLUMN ad_id integer REFERENCES ads(ad_id) ON DELETE CASCADE;
ALTER TABLE ad_image_xref ADD COLUMN image_id integer REFERENCES images(image_id) ON DELETE CASCADE;
ALTER TABLE ads DROP constraint ads_owner_fkey;
ALTER TABLE ads ADD constraint ads_owner_fkey FOREIGN KEY (owner) REFERENCES users(user_id) ON DELETE CASCADE;
ALTER TABLE ads DROP constraint ads_category_id_fkey;
ALTER TABLE ads ALTER COLUMN category_id SET DEFAULT 10;
ALTER TABLE ads ADD constraint ads_category_id_fkey FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET DEFAULT;