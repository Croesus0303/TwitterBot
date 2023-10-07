#!/usr/bin/env python
"""Django's command-line utility for administrative tasks."""
import logging
import os
import sys
import pika

from twitterApi.configuration.RabbitMqConsumer import RabbitMqConsumer


def main():
    """Run administrative tasks."""
    logging.basicConfig(level=logging.DEBUG)

    # Create a logger instance
    logger = logging.getLogger('my_logger')

    # Create a handler (e.g., output log messages to the console)
    handler = logging.StreamHandler()
    # Create a formatter for the log messages
    formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
    handler.setFormatter(formatter)

    # Add the handler to the logger
    logger.addHandler(handler)
    RabbitMqConsumer()
    os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'twitterApi.settings')
    try:
        from django.core.management import execute_from_command_line
    except ImportError as exc:
        raise ImportError(
            "Couldn't import Django. Are you sure it's installed and "
            "available on your PYTHONPATH environment variable? Did you "
            "forget to activate a virtual environment?"
        ) from exc
    execute_from_command_line(sys.argv)

if __name__ == '__main__':
    main()



