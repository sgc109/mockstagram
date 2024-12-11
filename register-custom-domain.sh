#!/bin/bash

# Domains to check
hosts=("mockstagram.com" "image.mockstagramcdn.com")

# Flag to track if any domain is missing
add_entry=false

# Check each domain and flag the ones that are missing
for host in "${hosts[@]}"; do
    if ! grep -q "$host" /etc/hosts; then
        add_entry=true
        missing_hosts+=("$host")  # Store the missing domain
    fi
done

if [ "$add_entry" = true ]; then
    # Ask the user for permission to modify /etc/hosts
    read -p "/etc/hosts modification requires system permissions. Do you agree? [Y/n] " choice
    # Default to 'Y' if the user presses Enter without typing anything
    choice=${choice:-Y}

    case "$choice" in
        [Yy]* )
            # Loop through missing domains and add only the missing ones
            for missing_host in "${missing_hosts[@]}"; do
                echo "127.0.0.1 $missing_host" | sudo tee -a /etc/hosts > /dev/null
            done
            echo "Missing domains added successfully."
            ;;
        * )
            echo "Domain addition canceled."
            ;;
    esac
else
    echo "All domains already exist in /etc/hosts."
fi
